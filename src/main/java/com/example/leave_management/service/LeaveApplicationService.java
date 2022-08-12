package com.example.leave_management.service;

import com.example.leave_management.Model.LeaveApplication;
import com.example.leave_management.wrapper.LeaveApplicationWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface LeaveApplicationService
{
    ResponseEntity<String> createLeaveApplication(Map<String,String> requestMap);

    ResponseEntity<String> update(Map<String, String> requestMap);

    //ResponseEntity<List<LeaveApplicationWrapper>> getAllLeaves();
    ResponseEntity<List<String>> findByStatus();
    ResponseEntity<List<LeaveApplicationWrapper>> retrieveEmployeeLeaveByDate(Date date1, Date date2);


}
