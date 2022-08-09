package com.example.leave_management.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApplicationUtils
{
    private ApplicationUtils()
    {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus)
    {
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}",httpStatus);
    }
}
