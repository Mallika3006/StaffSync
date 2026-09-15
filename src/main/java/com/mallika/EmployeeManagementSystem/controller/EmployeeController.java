package com.mallika.EmployeeManagementSystem.controller;

import com.mallika.EmployeeManagementSystem.model.Employee;
import com.mallika.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee employee) {

        Employee savedEmployee =
                employeeService.createEmployee(employee);

        return new ResponseEntity<>(
                savedEmployee,
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }

    // GET LOGGED-IN EMPLOYEE
    @GetMapping("/me")
    public ResponseEntity<Employee> getMyProfile(
            Authentication authentication) {

        String username = authentication.getName();

        Employee employee =
                employeeService.getMyProfile(username);

        return ResponseEntity.ok(employee);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Integer id,
            @RequestBody Employee employee) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, employee)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable Integer id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(
                "Employee deleted successfully"
        );
    }

    // SEARCH BY NAME
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployee(
            @RequestParam String name) {

        return ResponseEntity.ok(
                employeeService.searchByName(name)
        );
    }

    // SEARCH BY EMAIL
    @GetMapping("/email")
    public ResponseEntity<Employee> getEmployeeByEmail(
            @RequestParam String email) {

        return ResponseEntity.ok(
                employeeService.getEmployeeByEmail(email)
        );
    }

    // FILTER BY DESIGNATION
    @GetMapping("/designation/{designationId}")
    public ResponseEntity<List<Employee>> getEmployeesByDesignation(
            @PathVariable Integer designationId) {

        return ResponseEntity.ok(
                employeeService.getEmployeesByDesignation(designationId)
        );
    }

    // FILTER BY TEAM
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Employee>> getEmployeesByTeam(
            @PathVariable Integer teamId) {

        return ResponseEntity.ok(
                employeeService.getEmployeesByTeam(teamId)
        );
    }

    // SORT
    @GetMapping("/sort")
    public ResponseEntity<List<Employee>> getEmployeesSorted(
            @RequestParam(defaultValue = "firstName") String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                employeeService.getEmployeesSorted(field, direction)
        );
    }
}