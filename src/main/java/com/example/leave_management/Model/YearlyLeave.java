package com.example.leave_management.Model;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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

    private Integer year;

    private Integer maximumDays;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="leave_type_id",
            referencedColumnName = "leave_type_id"
    )
    private LeaveType leaveType;
}
