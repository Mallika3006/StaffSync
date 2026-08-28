package com.mallika.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceId;

    private LocalDate attDate;

    private String status;

    private LocalTime checkInTime;

    private LocalTime checkOutTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
