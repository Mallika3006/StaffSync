package com.mallika.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dependents")
public class Dependent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dependentId;

    private String dependentName;

    private String relationship;

    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
