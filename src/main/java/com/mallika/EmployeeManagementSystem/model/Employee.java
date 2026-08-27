package com.mallika.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int emp_id;
    String first_name;
    String last_name;
    @Column (unique = true)
    String email;
    @Column (unique = true)
    String phone;
    LocalDate d_o_b;
    LocalDate hire_date;
    String address;
    Integer designation_id;
    Integer team_id;

}
