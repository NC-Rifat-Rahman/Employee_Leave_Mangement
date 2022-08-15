package com.example.leave_management.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedQuery(name = "LeaveApplication.findByLeaveApplicationId",query = "select u from LeaveApplication u where u.id=:id")

@NamedQuery(name = "LeaveApplication.updateStatus", query = "update LeaveApplication u set u.status=:status where u.id=:id")

@NamedQuery(name = "LeaveApplication.getAllLeaves", query = "select new com.example.leave_management.wrapper.LeaveApplicationWrapper(p.id,p.remark,p.managerRemark,p.status,p.leaveType.name,p.user.email) from LeaveApplication p")

@NamedQuery(name = "LeaveApplication.findByLeaveTypeName", query = "SELECT l FROM LeaveApplication l WHERE l.leaveType.name=:name ")

@NamedQuery(name = "LeaveApplication.findByLeaveTypeStatus", query = "SELECT l FROM LeaveApplication l WHERE l.status=:status ")

@NamedQuery(name = "LeaveApplication.findByLeaveApplicationDate", query = "SELECT l FROM LeaveApplication l WHERE (l.fromDate BETWEEN ?1 AND ?2)")

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
    @Column(name="id")
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "to_date", nullable = false)
    private Date toDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Column(name = "remark", columnDefinition = "TEXT", nullable = false)
    private String remark;

    @Column(name="manager_remark")
    private String managerRemark;

    @Column(name="status")
    private String status;

    @ManyToOne(
            //cascade = CascadeType.ALL
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name="leave_type_id",
            referencedColumnName = "leave_type_id"
    )
    private LeaveType leaveType;


    @ManyToOne(
            //cascade = CascadeType.ALL
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name="user_id",
            referencedColumnName = "user_id"
    )
    private User user;
}




