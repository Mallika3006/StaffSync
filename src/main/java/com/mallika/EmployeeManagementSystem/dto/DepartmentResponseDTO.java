package com.mallika.EmployeeManagementSystem.dto;

import lombok.Data;

@Data
public class DepartmentResponseDTO {

    private Long departmentId;
    private String departmentName;
    private String location;
    private String description;
}