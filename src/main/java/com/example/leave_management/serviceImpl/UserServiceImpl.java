package com.example.leave_management.serviceImpl;


import com.example.leave_management.JWT.CustomerUsersDetailsService;
import com.example.leave_management.JWT.JwtFilter;
import com.example.leave_management.JWT.JwtUtil;
import com.example.leave_management.Model.User;
import com.example.leave_management.Repository.UserRepository;
import com.example.leave_management.constants.ApplicationConstants;
import com.example.leave_management.service.UserService;
import com.example.leave_management.utils.ApplicationUtils;
import com.example.leave_management.utils.EmailUtils;
import com.example.leave_management.wrapper.UserWrapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j //login
@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap)
    {
        log.info("Inside signup{}",requestMap);

        try
        {
            if(validateSighnUpMap(requestMap))
            {
                User user=userRepository.findByEmailId(requestMap.get("email"));
                if(Objects.isNull(user))
                {
                    userRepository.save(getUserFromMap(requestMap));
                    return ApplicationUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                }
                else
                {
                    return ApplicationUtils.getResponseEntity("Email already exists",HttpStatus.BAD_REQUEST);
                }
            }
            else
            {
                return ApplicationUtils.getResponseEntity(ApplicationConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private boolean validateSighnUpMap(Map<String,String> requestMap)
    {
        // It will come from UI(User class)
        if(requestMap.containsKey("name")
                && requestMap.containsKey("email") && requestMap.containsKey("password"))
        {
            return true;
        }
        else
            return false;
    }

    private User getUserFromMap(Map<String,String> requestMap)
    {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");

        return user;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");

        try
        {
            // Authenticate user
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password"))
            );

            // if user is approved
            if(auth.isAuthenticated())
            {
                if(customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true"))
                {
                    return new ResponseEntity<String>("{\"token\":\""+
                            jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                                    customerUsersDetailsService.getUserDetail().getRole())+ "\"}" ,
                            HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<String>("{\"message\":\""+ "Wait for admin approval"+"\"}",HttpStatus.BAD_REQUEST);
                }
            }
        }
        catch (Exception ex)
        {
            log.error("{}",ex);
        }
        return new ResponseEntity<String>("{\"message\":\""+ "Bad Credentials"+"\"}",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try
        {
            if(jwtFilter.isAdmin())
            {
                return new ResponseEntity<>(userRepository.getAllUser(),HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try
        {
            if(jwtFilter.isAdmin())
            {
                // if user exist in database or not
                Optional<User> optional = userRepository.findById(Integer.parseInt(requestMap.get("id")));

                if(!optional.isEmpty())
                {
                    userRepository.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));

                    sendMailToAllAdmin(requestMap.get("status"),optional.get().getEmail(),userRepository.getAllAdmin());

                    return ApplicationUtils.getResponseEntity("User status updated successfully!",HttpStatus.OK);
                }
                else
                {
                    return ApplicationUtils.getResponseEntity("User id does not exist",HttpStatus.OK);
                }
            }
            else
            {
                return ApplicationUtils.getResponseEntity(ApplicationConstants.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin)
    {
        allAdmin.remove(jwtFilter.getCurrentUser());

        if(status!=null && status.equalsIgnoreCase("true"))
        {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Approved","USER:- "+user+"\n is approved by \n Admin:-"+jwtFilter.getCurrentUser(),allAdmin);
        }
        else
        {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Disabled","USER:- "+user+"\n is disabled by \n Admin:-"+jwtFilter.getCurrentUser(),allAdmin);
        }
    }

    // Validate user. like Users cant access Admin pages
    @Override
    public ResponseEntity<String> checkToken() {
        return null;
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {

        try
        {
            // Extract user from token
            User userObj = userRepository.findByEmail(jwtFilter.getCurrentUser());

            if(!userObj.equals(null))
            {
                if(userObj.getPassword().equals(requestMap.get("oldPassword")))
                {
                    userObj.setPassword(requestMap.get("newPassword"));
                    userRepository.save(userObj);
                    return ApplicationUtils.getResponseEntity("Password updated Successfully",HttpStatus.OK);
                }
                return ApplicationUtils.getResponseEntity("Incorrect Old Password",HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try
        {
            User user = userRepository.findByEmail(requestMap.get("email"));
            if(!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail()))
                emailUtils.forgotMail(user.getEmail(),"Credentials from",user.getPassword());
            return ApplicationUtils.getResponseEntity("Check your mail from Credentials",HttpStatus.OK);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

