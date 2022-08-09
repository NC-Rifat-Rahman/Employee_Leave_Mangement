package com.example.leave_management.Repository;

import com.example.leave_management.Model.LeaveApplication;
import com.example.leave_management.Model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer>
{
    LeaveType findByLeaveTypeId(@Param("leaveTypeId") String leaveTypeId);

    LeaveType findByLeaveTypeName(String typeName);

   // List<LeaveType> findAllByLeaveTypeNameContaining(String typeName);
}