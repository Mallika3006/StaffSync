package com.mallika.EmployeeManagementSystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDTO {

    private Long employeeId;

    private String leaveType;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String reason;

    private String status;
}