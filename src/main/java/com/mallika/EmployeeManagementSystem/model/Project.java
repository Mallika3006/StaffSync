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
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    private String projectName;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Task> tasks;

    @ManyToMany(mappedBy = "projects")
    @JsonIgnore
    private List<Team> teams;
}
