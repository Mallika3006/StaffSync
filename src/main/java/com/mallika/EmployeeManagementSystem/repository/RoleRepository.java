package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    // Search role names
    List<Role> findByRoleNameContainingIgnoreCase(String roleName);

    // Exact role name
    Optional<Role> findByRoleNameIgnoreCase(String roleName);
}