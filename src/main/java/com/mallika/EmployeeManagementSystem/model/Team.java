package com.mallika.EmployeeManagementSystem.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    private String teamName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "team")
    private List<Employee> employees;

    @OneToMany(mappedBy = "team")
    private List<TeamProject> teamProjects;
}
