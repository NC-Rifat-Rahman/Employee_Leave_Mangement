package com.example.leave_management.rest;

import com.itextpdf.text.log.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.logging.Logger;

@RequestMapping(path = "/leave")
public interface LeaveRest
{
    //private static final Logger LOGGER = LoggerFactory.getLogger(LeaveRest.class);

    @GetMapping("/byDate")
    public ResponseEntity<?> retrieveEmployeeLeaveByDate(
            @RequestParam("date1") Date date1, @RequestParam("date2") Date date2);

}
