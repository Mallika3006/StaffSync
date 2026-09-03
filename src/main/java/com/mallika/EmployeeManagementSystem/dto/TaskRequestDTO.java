package com.mallika.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {

    @NotNull
    private Long projectId;

    @NotNull
    private Long employeeId;

    @NotBlank
    @Size(min = 2, max = 100)
    private String taskName;

    @Size(max = 500)
    private String description;

    @NotBlank
    private String priority;

    @NotBlank
    private String status;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate dueDate;

    private LocalDate completedDate;
}
