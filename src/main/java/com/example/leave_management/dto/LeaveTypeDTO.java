package com.example.leave_management.dto;

public class LeaveTypeDTO
{
    private Long leaveTypeId;

    private String name;

    private String status;

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getTypeName() {
        return name;
    }

    public void setTypeName(String typeName) {
        this.name = typeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
