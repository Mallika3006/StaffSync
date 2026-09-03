package com.mallika.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class TeamRequestDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String teamName;

    @NotNull
    private Long departmentId;

    @NotNull
    private Long teamLeaderId;

    @Size(max = 500)
    private String description;

    @NotNull
    @Size(min = 1)
    private Set<Long> projectIds;
}