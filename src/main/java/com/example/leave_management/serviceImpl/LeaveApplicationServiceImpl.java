package com.example.leave_management.serviceImpl;

import com.example.leave_management.JWT.JwtFilter;
import com.example.leave_management.Model.LeaveApplication;
import com.example.leave_management.Model.LeaveType;
import com.example.leave_management.Model.User;
import com.example.leave_management.Model.YearlyLeave;
import com.example.leave_management.Repository.LeaveApplicationRepository;
import com.example.leave_management.constants.ApplicationConstants;
import com.example.leave_management.service.LeaveApplicationService;
import com.example.leave_management.utils.ApplicationUtils;
import com.example.leave_management.wrapper.LeaveApplicationWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService
{
    @Autowired
    LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    JwtFilter jwtFilter;


    @Override
    public ResponseEntity<String> createLeaveApplication(Map<String, String> requestMap)
    {
        try
        {
            if(jwtFilter.isUser())
            {
                if(validateLeaveApplicationMap(requestMap,false))
                {
                    leaveApplicationRepository.save(getLeaveApplicationFromMap(requestMap,false));
                    return ApplicationUtils.getResponseEntity("Leave Application added",HttpStatus.OK);
                }
                return ApplicationUtils.getResponseEntity(ApplicationConstants.INVALID_DATA,HttpStatus.BAD_REQUEST);
            }
            else
                return ApplicationUtils.getResponseEntity(ApplicationConstants.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateLeaveApplicationMap(Map<String,String> requestMap, boolean validateId)
    {
        if(requestMap.containsKey("status"))
        {
            if(requestMap.containsKey("id") && validateId)
            {
                return true;
            }
            else if(!validateId)
            {
                return true;
            }
        }
        return false;
    }

    private LeaveApplication getLeaveApplicationFromMap(Map<String,String> requestMap,Boolean isAdd)
    {
        User user = new User();
        user.setUserId(Integer.parseInt(requestMap.get("user_id")));

        LeaveType leaveType = new LeaveType();
        leaveType.setLeaveTypeId(Integer.parseInt(requestMap.get("leave_type_id")));

        LeaveApplication leaveApplication = new LeaveApplication();

        if(isAdd)
        {
            leaveApplication.setId(Integer.parseInt(requestMap.get("id")));
        }
        else
        {
            leaveApplication.setStatus("true");
        }
        leaveApplication.setLeaveType(leaveType);
        leaveApplication.setUser(user);
        leaveApplication.setFromDate(Date.valueOf(requestMap.get("from_date")));
        leaveApplication.setToDate(Date.valueOf(requestMap.get("to_date")));
        leaveApplication.setRemark(requestMap.get("remark"));
        leaveApplication.setManagerRemark(requestMap.get("manager_remark"));
        leaveApplication.setStatus(requestMap.get("status"));
        return leaveApplication;
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try
        {
            if(jwtFilter.isManager())
            {
                //Optional<LeaveApplication> optional = leaveApplicationRepository.findById(Integer.parseInt(requestMap.get("id")));

                //if(!optional.isEmpty())
                //{
                    leaveApplicationRepository.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));

                    //sendMailToAllAdmin(requestMap.get("status"),optional.get().getEmail(),userRepository.getAllAdmin());

                    return ApplicationUtils.getResponseEntity("Leave status updated successfully!",HttpStatus.OK);
                //}
                //else
                //{
                   // return ApplicationUtils.getResponseEntity("LeaveApplication id does not exist",HttpStatus.OK);
                //}
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

/*
    @Override
    public ResponseEntity<List<LeaveApplicationWrapper>> getAllLeaves()
    {
        try
        {
            return new ResponseEntity<>(leaveApplicationRepository.getAllLeaves(),HttpStatus.OK);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

 */

    @Override
    public ResponseEntity<List<String>> findByStatus() {
        try
        {
            return new ResponseEntity<>(leaveApplicationRepository.findByStatus(),HttpStatus.OK);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<LeaveApplicationWrapper>> retrieveEmployeeLeaveByDate(Date date1, Date date2) {
        try
        {
            return new ResponseEntity<>(leaveApplicationRepository.findByLeaveApplicationByDate(date1,date2),HttpStatus.OK);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
