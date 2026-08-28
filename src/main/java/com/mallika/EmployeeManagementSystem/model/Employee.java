package com.mallika.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;

    private LocalDate dateOfBirth;

    private LocalDate hireDate;

    private String address;

    @ManyToOne
    @JoinColumn(name = "designation_id")
    private Designation designation;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "employee")
    private List<Payroll> payrolls;

    @OneToMany(mappedBy = "employee")
    private List<Leave> leaves;

    @OneToMany(mappedBy = "employee")
    private List<Dependent> dependents;

    @OneToOne(mappedBy = "employee")
    private User user;
}