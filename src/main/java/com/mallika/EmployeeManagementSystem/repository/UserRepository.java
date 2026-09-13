package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Exact username
    Optional<User> findByUsernameIgnoreCase(String username);

    // Search username
    List<User> findByUsernameContainingIgnoreCase(String username);

    // Active / inactive users
    List<User> findByIsActive(Boolean isActive);

    // User by employee
    Optional<User> findByEmployeeEmployeeId(Integer employeeId);

    // Users by role
    List<User> findByRoleRoleId(Integer roleId);

    // Users by role and active status
    List<User> findByRoleRoleIdAndIsActive(
            Integer roleId,
            Boolean isActive
    );
}
