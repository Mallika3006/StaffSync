package com.mallika.EmployeeManagementSystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {

    private Long projectId;

    private Long employeeId;

    private String taskName;

    private String description;

    private String priority;

    private String status;

    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate completedDate;
}
