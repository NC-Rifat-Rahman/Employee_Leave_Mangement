package com.example.leave_management.restImpl;

import com.example.leave_management.constants.ApplicationConstants;
import com.example.leave_management.rest.YearlyLeaveRest;
import com.example.leave_management.service.YearlyLeaveService;
import com.example.leave_management.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class YearlyLeaveRestImpl implements YearlyLeaveRest
{
    @Autowired
    YearlyLeaveService yearlyLeaveService;

    @Override
    public ResponseEntity<String> allocateYearlyLeave(Map<String, String> requestMap)
    {
        try
        {
            return yearlyLeaveService.allocateYearlyLeave(requestMap);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ApplicationUtils.getResponseEntity(ApplicationConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}