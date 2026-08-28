package com.mallika.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "leave_types")
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaveTypeId;

    private String typeName;

    private Integer maxDays;

    private String description;

    @OneToMany(mappedBy = "leaveType")
    private List<Leave> leaves;
}
