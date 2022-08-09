package com.example.leave_management.service;

import com.example.leave_management.Model.LeaveType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LeaveTypeService
{
    ResponseEntity<String> createLeaveType(Map<String,String> requestMap);
}
