package com.example.leave_management.wrapper;

import com.example.leave_management.Model.LeaveType;
import com.example.leave_management.Model.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
public class LeaveApplicationWrapper
{

     Integer id;

     String remark;

     String managerRemark;

     String status;

     String leaveType;

     String useremail;

     String from_date;

    String to_date;

    public LeaveApplicationWrapper() {
    }

    public LeaveApplicationWrapper(Integer id, String remark, String managerRemark, String status, String leaveType, String useremail) {
        this.id = id;
        this.remark = remark;
        this.managerRemark = managerRemark;
        this.status = status;
        this.leaveType = leaveType;
        this.useremail = useremail;
    }
}
