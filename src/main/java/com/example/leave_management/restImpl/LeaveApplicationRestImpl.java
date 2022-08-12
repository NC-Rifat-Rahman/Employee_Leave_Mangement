package com.example.leave_management.restImpl;

import com.example.leave_management.constants.ApplicationConstants;
import com.example.leave_management.rest.LeaveApplicationRest;
import com.example.leave_management.service.LeaveApplicationService;
import com.example.leave_management.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveApplicationRestImpl implements LeaveApplicationRest
{
    @Autowired
    LeaveApplicationService leaveApplicationService;
    @Override
    public ResponseEntity<String> createLeaveApplication(Map<String, String> requestMap)
    {
        try
        {
            return leaveApplicationService.createLeaveApplication(requestMap);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap)
    {
        try
        {
            return leaveApplicationService.update(requestMap);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<String>> findByStatus()
    {
        try
        {
            return leaveApplicationService.findByStatus();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
    @Override
    public ResponseEntity<List<LeaveApplicationWrapper>> getAllLeaves() {
        try
        {
            return leaveApplicationService.getAllLeaves();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

     */
}
