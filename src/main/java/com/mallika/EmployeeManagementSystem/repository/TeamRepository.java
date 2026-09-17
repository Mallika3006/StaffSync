package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Employee;
import com.mallika.EmployeeManagementSystem.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findByTeamNameIgnoreCase(String teamName);

    List<Team> findByTeamNameContainingIgnoreCase(String teamName);

    List<Team> findByDepartmentDepartmentId(Integer departmentId);

    Optional<Team> findByEmployeesEmployeeId(Integer employeeId);
}