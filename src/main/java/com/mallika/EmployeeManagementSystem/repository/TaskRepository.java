package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    // Tasks of a project
    List<Task> findByProjectProjectId(Integer projectId);

    // Tasks assigned to an employee
    List<Task> findByAssignedToEmployeeId(Integer employeeId);

    // Search by task name
    List<Task> findByTaskNameContainingIgnoreCase(String taskName);

    // Filter by status
    List<Task> findByStatusIgnoreCase(String status);

    // Filter by priority
    List<Task> findByPriorityIgnoreCase(String priority);

    // Employee + status
    List<Task> findByAssignedToEmployeeIdAndStatusIgnoreCase(
            Integer employeeId,
            String status
    );

    // Project + status
    List<Task> findByProjectProjectIdAndStatusIgnoreCase(
            Integer projectId,
            String status
    );

    // Due date
    List<Task> findByDueDate(LocalDate dueDate);

    // Tasks between due dates
    List<Task> findByDueDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    // Tasks that are overdue
    List<Task> findByDueDateBeforeAndStatusNotIgnoreCase(
            LocalDate date,
            String status
    );
}