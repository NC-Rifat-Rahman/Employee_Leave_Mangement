package com.example.leave_management.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/yearlyLeave")
public interface YearlyLeaveRest
{
    @PostMapping(path="/allocateYearlyLeave")
    ResponseEntity<String> allocateYearlyLeave(@RequestBody(required = true) Map<String,String> requestMap);
}