package com.mallika.EmployeeManagementSystem.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TeamResponseDTO {

    private Long teamId;

    private String teamName;

    private Long departmentId;

    private Long teamLeaderId;

    private String description;

    private Set<Long> projectIds;
}
