package com.mallika.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequestDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String roleName;

    @Size(max = 500)
    private String description;
}