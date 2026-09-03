package com.mallika.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceRequestDTO {

    @NotNull
    private Long employeeId;

    @NotNull
    private LocalDate attendanceDate;

    @NotBlank
    private String status;

    private LocalTime checkInTime;

    private LocalTime checkOutTime;
}