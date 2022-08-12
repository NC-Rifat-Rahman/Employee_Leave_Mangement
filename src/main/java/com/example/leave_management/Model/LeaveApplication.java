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
import java.time.LocalDateTime;

@NamedQuery(name = "LeaveApplication.findByLeaveApplicationId",query = "select u from LeaveApplication u where u.id=:id")

@NamedQuery(name = "LeaveApplication.updateStatus", query = "update LeaveApplication u set u.status=:status where u.id=:id")

//@NamedQuery(name = "LeaveApplication.getAllLeaves", query = "select new com.example.leave_management.wrapper.LeaveApplicationWrapper(p.id,p.remark,p.managerRemark,p.status,p.leaveType.name,p.user.email) from LeaveApplication p")


@NamedQuery(name = "LeaveApplication.findByLeaveApplicationByDate", query = "SELECT l FROM LeaveApplication l WHERE ((l.fromDate BETWEEN ?1 AND ?2) OR (l.toDate BETWEEN ?1 AND ?2)) " +
                "AND l.status='APPROVED' ")

@NamedQuery(name = "LeaveApplication.findByStatus", query = "select new com.example.leave_management.wrapper.LeaveApplicationWrapper(p.id,p.remark,p.managerRemark,p.status,p.leaveType.name,p.user.email) from LeaveApplication p")

/*
@NamedNativeQuery(
        name = "LeaveApplication.generateLeaveReport",
        query =
                "select count(leave_id) as count, lt.type_name AS leaveType, month(lr.from_date) as month, lr.status as status\n" +
                        "from leave_request lr \n" +
                        "    INNER JOIN employee e ON e.employee_id = lr.employee_id\n" +
                        "\tINNER JOIN leave_type lt ON lt.leave_type_id = lr.leave_type\n" +
                        "    where \n" +
                        "    lr.from_date >= date_add(curdate(),INTERVAL -1 YEAR) OR\n" +
                        "    lr.to_date >= date_add(curdate(), INTERVAL -1 YEAR)\n" +
                        "    group by lt.type_name, month(lr.from_date), lr.status\n" +
                        "    order by month(lr.from_date)",
        resultSetMapping = "LeaveReportDTO"
)

@SqlResultSetMapping(
        name = "LeaveReportDTO",
        classes = @ConstructorResult(
                targetClass = LeaveReportDTO.class,
                columns = {
                        @ColumnResult(name = "count", type = Integer.class),
                        @ColumnResult(name = "leaveType", type = String.class),
                        @ColumnResult(name = "month", type = Integer.class),
                        @ColumnResult(name = "status", type = String.class)
                }
        )
)

 */



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
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "to_date", nullable = false)
    private Date toDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
   // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Column(name = "remark", columnDefinition = "TEXT", nullable = false)
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




