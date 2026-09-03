package com.mallika.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PayrollRequestDTO {

    @NotNull
    private Long employeeId;

    @NotBlank
    @Size(min = 7, max = 7)
    private String payMonth;

    @NotNull
    private BigDecimal basicSalary;

    @NotNull
    private BigDecimal allowances;

    @NotNull
    private BigDecimal deductions;

    @NotNull
    private BigDecimal netSalary;

    @NotNull
    private LocalDate paymentDate;
}
