package com.example.leave_management.serviceImpl;

import com.example.leave_management.JWT.JwtFilter;
import com.example.leave_management.Model.LeaveType;
import com.example.leave_management.Repository.LeaveTypeRepository;
import com.example.leave_management.constants.ApplicationConstants;
import com.example.leave_management.service.LeaveTypeService;
import com.example.leave_management.utils.ApplicationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class LeaveTypeServiceImpl implements LeaveTypeService
{
    @Autowired
    JwtFilter jwtFilter;

    //@Autowired
    //LeaveType leaveType;

    @Autowired
    LeaveTypeRepository leaveTypeRepository;


    @Override
    public ResponseEntity<String> createLeaveType(Map<String,String> requestMap)
    {
        try
        {
            if(jwtFilter.isAdmin())
            {
                if(validateLeaveTypeMap(requestMap,false))
                {
                    leaveTypeRepository.save(getLeaveTypeFromMap(requestMap,false));
                    return ApplicationUtils.getResponseEntity("Leave Type Added Successfully", HttpStatus.OK);
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
        return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateLeaveTypeMap(Map<String,String> requestMap, boolean validateId)
    {
        if(requestMap.containsKey("name"))
        {
            if(requestMap.containsKey("leaveTypeId") && validateId && requestMap.containsKey("status"))
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

    private LeaveType getLeaveTypeFromMap(Map<String,String> requestMap,Boolean isAdd)
    {
        LeaveType leaveType = new LeaveType();
        if(isAdd)
        {
            leaveType.setLeaveTypeId(Integer.parseInt(requestMap.get("leaveTypeId")));
        }
        leaveType.setName(requestMap.get("name"));
        return leaveType;
    }
}
