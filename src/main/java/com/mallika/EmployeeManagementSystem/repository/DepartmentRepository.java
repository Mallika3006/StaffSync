package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findByDepartmentNameIgnoreCase(String departmentName);

    List<Department> findByDepartmentNameContainingIgnoreCase(String departmentName);

    List<Department> findByLocationContainingIgnoreCase(String location);
}
