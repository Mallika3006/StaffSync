package com.mallika.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentRequestDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String departmentName;

    @NotBlank
    @Size(min = 2, max = 100)
    private String location;

    @Size(max = 500)
    private String description;
}
