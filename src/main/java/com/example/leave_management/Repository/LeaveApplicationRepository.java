package com.example.leave_management.Repository;

import com.example.leave_management.Model.LeaveApplication;
import com.example.leave_management.Model.User;
import com.example.leave_management.wrapper.LeaveApplicationWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,Integer>
{
    LeaveApplication findByLeaveApplicationId(@Param("id") String leaveApplicationId);

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status,@Param("id") Integer id);

    List<LeaveApplicationWrapper> getAllLeaves();

    List<LeaveApplication> findByLeaveTypeName(String name);

    List<LeaveApplication> findByLeaveTypeStatus(String status);

    List<LeaveApplication> findByLeaveApplicationDate(Date date1, Date date2);

}



