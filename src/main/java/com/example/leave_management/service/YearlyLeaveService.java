package com.example.leave_management.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface YearlyLeaveService
{
    ResponseEntity<String> allocateYearlyLeave(Map<String,String> requestMap);
}
