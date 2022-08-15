package com.example.leave_management.Model;


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

    @Column(name="name")
    private String name;

    @Column(name="remark")
    private String remark;
}

