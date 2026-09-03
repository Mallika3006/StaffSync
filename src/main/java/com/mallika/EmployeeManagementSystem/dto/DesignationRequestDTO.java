package com.mallika.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DesignationRequestDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String designationTitle;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal minSalary;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal maxSalary;
}
