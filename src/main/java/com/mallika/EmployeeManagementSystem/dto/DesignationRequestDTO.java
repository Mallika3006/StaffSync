package com.mallika.EmployeeManagementSystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DesignationRequestDTO {

    private String designationTitle;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
