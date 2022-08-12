package com.example.leave_management.restImpl;

import com.example.leave_management.service.LeaveApplicationService;
import com.itextpdf.text.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.logging.Logger;

@RestController
public class LeaveRestImpl
{
    @Autowired
    LeaveApplicationService leaveApplicationService;
    //private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LeaveRestImpl.class);
    @GetMapping("/byDate")
    public ResponseEntity<?> retrieveEmployeeLeaveByDate(
            @RequestParam("date1") Date date1,
            @RequestParam("date2") Date date2){
        //LOGGER.info("API Retrieve Leave By Date");
        return new ResponseEntity<>(leaveApplicationService.retrieveEmployeeLeaveByDate(date1, date2), HttpStatus.OK);
    }
}
