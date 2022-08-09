package com.example.leave_management.restImpl;

import com.example.leave_management.Model.LeaveType;
import com.example.leave_management.constants.ApplicationConstants;
import com.example.leave_management.rest.LeaveTypeRest;
import com.example.leave_management.service.LeaveTypeService;
import com.example.leave_management.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LeaveRestImpl implements LeaveTypeRest
{
    @Autowired
    LeaveTypeService leaveTypeService;

    @Override
    public ResponseEntity<String> addNewLeaveType(Map<String, String> requestMap)
    {
        try
        {
            return leaveTypeService.createLeaveType(requestMap);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
