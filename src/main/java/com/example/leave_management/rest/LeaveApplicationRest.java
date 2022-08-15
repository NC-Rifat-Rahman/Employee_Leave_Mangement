package com.example.leave_management.rest;

import com.example.leave_management.Model.LeaveApplication;
import com.example.leave_management.wrapper.LeaveApplicationWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/leaveApplication")
public interface LeaveApplicationRest
{
    @PostMapping(path ="/create")
    ResponseEntity<String> createLeaveApplication(@RequestBody(required = true)
                                          Map<String,String> requestMap);
    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String,String> requestMap);

    @GetMapping(path = "/get")
    ResponseEntity<List<LeaveApplicationWrapper>> getAllLeaves();

    @RequestMapping(value="/leaveType/{name}")
    public ResponseEntity<List<LeaveApplication>> getLeaveApplicationByLeaveType(@PathVariable String name);

    @RequestMapping(value="/leaveType/status/{status}")
    public List<LeaveApplication> getLeaveApplicationByLeaveTypeStatus(@PathVariable String status);

    @RequestMapping("/leaveType/byDate")
    public List<LeaveApplication> getLeaveApplicationByDate(@RequestParam Date date1, @RequestParam Date date2);

}