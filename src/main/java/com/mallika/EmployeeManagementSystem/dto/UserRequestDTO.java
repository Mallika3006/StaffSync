package com.mallika.EmployeeManagementSystem.dto;

import lombok.Data;

@Data
public class UserRequestDTO {

    private Long employeeId;

    private String username;

    private String password;

    private Long roleId;

    private Boolean isActive;
}
