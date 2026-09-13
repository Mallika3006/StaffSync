package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    // Search by project name
    List<Project> findByProjectNameContainingIgnoreCase(String projectName);

    // Exact project name
    List<Project> findByProjectNameIgnoreCase(String projectName);

    // Projects starting on a specific date
    List<Project> findByStartDate(LocalDate startDate);

    // Projects ending on a specific date
    List<Project> findByEndDate(LocalDate endDate);

    // Projects within a date range
    List<Project> findByStartDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    // Projects that are still ongoing
    List<Project> findByEndDateIsNull();
}