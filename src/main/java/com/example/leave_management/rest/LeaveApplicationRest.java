package com.example.leave_management.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
   // @GetMapping(path = "/get")
    //ResponseEntity<List<LeaveApplicationWrapper>> getAllLeaves();

    @GetMapping(path = "/get")
    ResponseEntity<List<String>> findByStatus();

}