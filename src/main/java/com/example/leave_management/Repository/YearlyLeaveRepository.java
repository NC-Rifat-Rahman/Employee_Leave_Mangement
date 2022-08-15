package com.example.leave_management.Repository;

import com.example.leave_management.Model.YearlyLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface YearlyLeaveRepository extends JpaRepository<YearlyLeave,Integer>
{
    YearlyLeave findByYearlyLeaveId(@Param("yearlyLeaveId") String yearlyLeaveId);

    String findByMaximumDays();
}
