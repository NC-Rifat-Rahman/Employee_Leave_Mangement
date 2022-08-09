package com.example.leave_management.Model;

import com.example.leave_management.utils.enums.LeaveStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@NamedQuery(name = "LeaveApplication.findByLeaveApplicationId",query = "select u from LeaveApplication u where u.leaveApplicationId=:leaveApplicationId")


@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="leaveapplication")
public class LeaveApplication implements Serializable
{

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="leave_application_id")
    private Integer leaveApplicationId;

    @Column(name = "today", nullable = false)
    @CreationTimestamp
    private Date today;

    @Column(name = "formdate", nullable = false)
    @CreationTimestamp
    private LocalDateTime formDate;

    @Column(name="remark")
    private String remark;

    @Column(name="manager_remark")
    private String managerRemark;

    /*
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

     */
    @Column(name="status")
    private String status;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="leave_type_id",
            referencedColumnName = "leave_type_id"
    )
    private LeaveType leaveType;


    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="user_id",
            referencedColumnName = "user_id"
    )
    private User user;
}




