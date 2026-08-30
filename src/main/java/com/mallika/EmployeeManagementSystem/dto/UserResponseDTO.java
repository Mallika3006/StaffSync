package com.mallika.EmployeeManagementSystem.dto;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long userId;

    private Long employeeId;

    private String username;

    private Long roleId;

    private Boolean isActive;
}