package com.mallika.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ProjectRequestDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String projectName;

    @NotNull
    private Long projectManagerId;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotBlank
    private String status;

    @Size(max = 500)
    private String description;

    @NotNull
    @Size(min = 1)
    private Set<Long> teamIds;
}
