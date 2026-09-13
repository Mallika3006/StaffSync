package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DesignationRepository extends JpaRepository<Designation, Integer> {

    Optional<Designation> findByDesignationTitleIgnoreCase(String designationTitle);

    List<Designation> findByDesignationTitleContainingIgnoreCase(String designationTitle);
}
