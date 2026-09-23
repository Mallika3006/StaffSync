package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.User;
import com.mallika.EmployeeManagementSystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // CREATE
    public User createUser(User user) {

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        return userRepository.createUser(user);
    }


    // GET ALL
    public List<User> getAllUsers() {

        return userRepository.getAllUsers();
    }


    // GET BY ID
    public User getUserById(Integer id) {

        return userRepository.getUserById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        )
                );
    }


    // UPDATE
    public User updateUser(
            Integer id,
            User userDetails) {

        // Check whether user exists
        getUserById(id);

        userDetails.setPassword(
                passwordEncoder.encode(
                        userDetails.getPassword()
                )
        );

        return userRepository.updateUser(
                id,
                userDetails
        );
    }


    // DELETE
    public void deleteUser(Integer id) {

        // Check whether user exists
        getUserById(id);

        userRepository.deleteUser(id);
    }


    // SEARCH BY USERNAME
    public List<User> searchByUsername(
            String username) {

        return userRepository
                .findByUsernameContainingIgnoreCase(username);
    }


    // EXACT USERNAME
    public User getByUsername(
            String username) {

        return userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with username: "
                                        + username
                        )
                );
    }


    // ACTIVE / INACTIVE
    public List<User> getUsersByActiveStatus(
            Boolean isActive) {

        return userRepository.findByIsActive(isActive);
    }


    // BY EMPLOYEE
    public User getUserByEmployee(
            Integer employeeId) {

        return userRepository
                .findByEmployeeEmployeeId(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found for employee id: "
                                        + employeeId
                        )
                );
    }


    // BY ROLE
    public List<User> getUsersByRole(
            Integer roleId) {

        return userRepository
                .findByRoleRoleId(roleId);
    }


    // ROLE + ACTIVE STATUS
    public List<User> getUsersByRoleAndStatus(
            Integer roleId,
            Boolean isActive) {

        return userRepository
                .findByRoleRoleIdAndIsActive(
                        roleId,
                        isActive
                );
    }


    // SORT
    public List<User> sortUsers(
            String field,
            String direction) {

        return userRepository.sortUsers(
                field,
                direction
        );
    }
}