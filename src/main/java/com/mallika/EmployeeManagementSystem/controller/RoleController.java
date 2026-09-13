package com.mallika.EmployeeManagementSystem.controller;

import com.mallika.EmployeeManagementSystem.model.Role;
import com.mallika.EmployeeManagementSystem.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Role> createRole(
            @RequestBody Role role) {

        return new ResponseEntity<>(
                roleService.createRole(role),
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {

        return ResponseEntity.ok(
                roleService.getAllRoles()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                roleService.getRoleById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(
            @PathVariable Integer id,
            @RequestBody Role role) {

        return ResponseEntity.ok(
                roleService.updateRole(id, role)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(
            @PathVariable Integer id) {

        roleService.deleteRole(id);

        return ResponseEntity.noContent().build();
    }

    // SEARCH BY NAME
    @GetMapping("/search")
    public ResponseEntity<List<Role>> searchByRoleName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                roleService.searchByRoleName(name)
        );
    }

    // EXACT ROLE NAME
    @GetMapping("/name")
    public ResponseEntity<Role> getByExactRoleName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                roleService.getByExactRoleName(name)
        );
    }

    // SORT
    @GetMapping("/sort")
    public ResponseEntity<List<Role>> sortRoles(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                roleService.sortRoles(field, direction)
        );
    }
}
