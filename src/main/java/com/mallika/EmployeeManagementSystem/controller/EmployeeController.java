package com.mallika.EmployeeManagementSystem.controller;

import com.mallika.EmployeeManagementSystem.dto.EmployeeRequestDTO;
import com.mallika.EmployeeManagementSystem.dto.EmployeeResponseDTO;
import com.mallika.EmployeeManagementSystem.exception.EmployeeNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Employee;
import com.mallika.EmployeeManagementSystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFound(
            EmployeeNotFoundException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO dto) {

        EmployeeResponseDTO employee = employeeService.createEmployee(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Integer id,
            @Valid @RequestBody EmployeeRequestDTO dto) {

        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestParam String firstName) {

        return ResponseEntity.ok(
                employeeService.searchEmployeesByFirstName(firstName)
        );
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getById(id);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Employee>> filterEmployees(
            @RequestParam String email) {

        return ResponseEntity.ok(
                employeeService.filterEmployeesByEmail(email)
        );
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Employee>> sortEmployees() {

        return ResponseEntity.ok(
                employeeService.sortEmployeesByFirstName()
        );
    }
}
