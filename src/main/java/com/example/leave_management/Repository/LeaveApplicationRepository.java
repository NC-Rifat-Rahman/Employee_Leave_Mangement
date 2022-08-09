package com.example.leave_management.Repository;

import com.example.leave_management.Model.LeaveApplication;
import com.example.leave_management.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,Integer>
{
    LeaveApplication findByLeaveApplicationId(@Param("leaveApplicationId") String leaveApplicationId);
}



