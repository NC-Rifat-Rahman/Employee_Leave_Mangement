package com.example.leave_management.Model;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NamedQuery(name = "YearlyLeave.findByYearlyLeaveId",query = "select u from YearlyLeave u where u.yearlyLeaveId=:yearlyLeaveId")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="yearlyleave")
public class YearlyLeave
{
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="yearly_leave_id")
    private Integer yearlyLeaveId;

    @Column(name="year")
    private Integer year;

    @Column(name="maximum_days")
    private Integer maximumDays;


    @ManyToOne()
    @JoinColumn(
            name="leave_type_id",
            referencedColumnName = "leave_type_id"
    )
    private LeaveType leaveType;
}
