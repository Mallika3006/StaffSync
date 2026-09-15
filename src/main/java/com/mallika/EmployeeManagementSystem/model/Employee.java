package com.mallika.EmployeeManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Payroll> payrolls;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Leave> leaves;

    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private User user;
}