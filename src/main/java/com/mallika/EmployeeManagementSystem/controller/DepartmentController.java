package com.mallika.EmployeeManagementSystem.controller;

import com.mallika.EmployeeManagementSystem.model.Department;
import com.mallika.EmployeeManagementSystem.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Department> createDepartment(
            @RequestBody Department department) {

        Department savedDepartment =
                departmentService.createDepartment(department);

        return new ResponseEntity<>(
                savedDepartment,
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {

        return ResponseEntity.ok(
                departmentService.getAllDepartments()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Integer id,
            @RequestBody Department department) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, department)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable Integer id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.ok(
                "Department deleted successfully"
        );
    }

    // SEARCH BY NAME
    @GetMapping("/search")
    public ResponseEntity<List<Department>> searchByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                departmentService.searchByName(name)
        );
    }

    // GET BY EXACT NAME
    @GetMapping("/name")
    public ResponseEntity<Department> getDepartmentByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                departmentService.getDepartmentByName(name)
        );
    }

    // SEARCH BY LOCATION
    @GetMapping("/location")
    public ResponseEntity<List<Department>> getDepartmentsByLocation(
            @RequestParam String location) {

        return ResponseEntity.ok(
                departmentService.getDepartmentsByLocation(location)
        );
    }

    // GET LOGGED-IN EMPLOYEE'S DEPARTMENT
    @GetMapping("/me")
    public ResponseEntity<Department> getMyDepartment(
            Authentication authentication) {

        String username = authentication.getName();

        return ResponseEntity.ok(
                departmentService.getDepartmentByUsername(username)
        );
    }
}
