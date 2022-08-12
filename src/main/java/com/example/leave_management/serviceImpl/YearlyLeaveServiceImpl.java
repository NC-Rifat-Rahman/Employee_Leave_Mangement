package com.example.leave_management.serviceImpl;

import com.example.leave_management.JWT.JwtFilter;
import com.example.leave_management.Model.LeaveType;
import com.example.leave_management.Model.YearlyLeave;
import com.example.leave_management.Repository.YearlyLeaveRepository;
import com.example.leave_management.constants.ApplicationConstants;
import com.example.leave_management.service.YearlyLeaveService;
import com.example.leave_management.utils.ApplicationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class YearlyLeaveServiceImpl implements YearlyLeaveService
{

    @Autowired
    YearlyLeaveRepository yearlyLeaveRepository;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> allocateYearlyLeave(Map<String, String> requestMap)
    {
        try
        {
            if(jwtFilter.isAdmin())
            {
                if(validateYearlyLeaveMap(requestMap,false))
                {
                    yearlyLeaveRepository.save(getYearlyLeaveFromMap(requestMap,false));
                    return ApplicationUtils.getResponseEntity("Yearly Leave Added Successfully", HttpStatus.OK);
                }
                return ApplicationUtils.getResponseEntity(ApplicationConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);

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

    private boolean validateYearlyLeaveMap(Map<String, String> requestMap, boolean validateId)
    {
        if(requestMap.containsKey("maximum_days"))
        {
            if(requestMap.containsKey("yearly_leave_id") && validateId && requestMap.containsKey("year"))
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

    private YearlyLeave getYearlyLeaveFromMap(Map<String, String> requestMap, boolean isAdd)
    {
        LeaveType leaveType = new LeaveType();
        leaveType.setLeaveTypeId(Integer.parseInt(requestMap.get("leave_type_id")));

        YearlyLeave yearlyLeave = new YearlyLeave();

        if(isAdd)
        {
            yearlyLeave.setYearlyLeaveId(Integer.parseInt(requestMap.get("yearly_leave_id")));
        }
        else
        {
            yearlyLeave.setMaximumDays(30);
        }

        yearlyLeave.setLeaveType(leaveType);
        yearlyLeave.setYear(Integer.valueOf(requestMap.get("year")));
        yearlyLeave.setMaximumDays(Integer.valueOf(requestMap.get("maximum_days")));
        return yearlyLeave;

    }
}