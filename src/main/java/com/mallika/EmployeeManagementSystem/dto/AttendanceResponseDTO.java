package com.mallika.EmployeeManagementSystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceResponseDTO {

    private Long attendanceId;

    private Long employeeId;

    private LocalDate attendanceDate;

    private String status;

    private LocalTime checkInTime;

    private LocalTime checkOutTime;
}
