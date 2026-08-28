package com.mallika.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payrollId;

    private String payMonth;

    private BigDecimal basicSalary;

    private BigDecimal allowances;

    private BigDecimal deductions;

    private BigDecimal netSalary;

    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
