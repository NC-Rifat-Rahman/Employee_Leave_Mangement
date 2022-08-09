package com.example.leave_management.Model;

import com.example.leave_management.utils.enums.LeaveTypeStatus;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;


@NamedQuery(name = "LeaveType.leaveTypeId",query = "select u from LeaveType u where u.leaveTypeId=:leaveTypeId")

@NamedQuery(name = "LeaveType.findByLeaveTypeName",query = "select u from LeaveType u where u.name=:name")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="leavetype")
public class LeaveType implements Serializable
{
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="leave_type_id")
    private Integer leaveTypeId;

    private String name;
/*
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LeaveTypeStatus status;

 */
    @Column(name="status")
    private String status;
}

