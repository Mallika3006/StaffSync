package com.mallika.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Designation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer designationId;

    private String designationTitle;

    private BigDecimal minSalary;

    private BigDecimal maxSalary;

    @OneToMany(mappedBy = "designation")
    private List<Employee> employees;
}
