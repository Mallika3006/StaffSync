package com.mallika.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDTO {

    @NotNull
    private Long employeeId;

    @NotBlank
    private String leaveType;

    @NotNull
    private LocalDate fromDate;

    @NotNull
    private LocalDate toDate;

    @Size(max = 500)
    private String reason;

    @NotBlank
    private String status;
}