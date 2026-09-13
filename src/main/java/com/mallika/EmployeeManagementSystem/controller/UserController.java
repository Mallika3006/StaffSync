package com.mallika.EmployeeManagementSystem.controller;

import com.mallika.EmployeeManagementSystem.model.User;
import com.mallika.EmployeeManagementSystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody User user) {

        return new ResponseEntity<>(
                userService.createUser(user),
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Integer id,
            @RequestBody User user) {

        return ResponseEntity.ok(
                userService.updateUser(id, user)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Integer id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    // SEARCH BY USERNAME
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchByUsername(
            @RequestParam String username) {

        return ResponseEntity.ok(
                userService.searchByUsername(username)
        );
    }

    // EXACT USERNAME
    @GetMapping("/username")
    public ResponseEntity<User> getByUsername(
            @RequestParam String username) {

        return ResponseEntity.ok(
                userService.getByUsername(username)
        );
    }

    // ACTIVE / INACTIVE
    @GetMapping("/status")
    public ResponseEntity<List<User>> getUsersByActiveStatus(
            @RequestParam Boolean isActive) {

        return ResponseEntity.ok(
                userService.getUsersByActiveStatus(isActive)
        );
    }

    // BY EMPLOYEE
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<User> getUserByEmployee(
            @PathVariable Integer employeeId) {

        return ResponseEntity.ok(
                userService.getUserByEmployee(employeeId)
        );
    }

    // BY ROLE
    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<User>> getUsersByRole(
            @PathVariable Integer roleId) {

        return ResponseEntity.ok(
                userService.getUsersByRole(roleId)
        );
    }

    // ROLE + ACTIVE STATUS
    @GetMapping("/role/{roleId}/status")
    public ResponseEntity<List<User>> getUsersByRoleAndStatus(
            @PathVariable Integer roleId,
            @RequestParam Boolean isActive) {

        return ResponseEntity.ok(
                userService.getUsersByRoleAndStatus(
                        roleId,
                        isActive
                )
        );
    }

    // SORT
    @GetMapping("/sort")
    public ResponseEntity<List<User>> sortUsers(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                userService.sortUsers(field, direction)
        );
    }
}