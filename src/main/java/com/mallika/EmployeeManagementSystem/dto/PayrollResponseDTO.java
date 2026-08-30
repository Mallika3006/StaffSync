package com.mallika.EmployeeManagementSystem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PayrollResponseDTO {

    private Long payrollId;

    private Long employeeId;

    private String payMonth;

    private BigDecimal basicSalary;

    private BigDecimal allowances;

    private BigDecimal deductions;

    private BigDecimal netSalary;

    private LocalDate paymentDate;
}
