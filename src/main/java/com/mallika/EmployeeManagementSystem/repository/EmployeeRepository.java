package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    List<Employee> findByLastNameContainingIgnoreCase(String lastName);

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDesignationDesignationId(Integer designationId);

    List<Employee> findByTeamTeamId(Integer teamId);

    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );
}