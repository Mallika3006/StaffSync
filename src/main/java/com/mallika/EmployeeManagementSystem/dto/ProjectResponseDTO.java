package com.mallika.EmployeeManagementSystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ProjectResponseDTO {

    private Long projectId;

    private String projectName;

    private Long projectManagerId;

    private LocalDate startDate;
    private LocalDate endDate;

    private String status;

    private String description;

    private Set<Long> teamIds;
}
