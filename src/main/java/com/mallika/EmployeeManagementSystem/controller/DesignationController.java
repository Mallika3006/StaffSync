package com.mallika.EmployeeManagementSystem.controller;

import com.mallika.EmployeeManagementSystem.model.Designation;
import com.mallika.EmployeeManagementSystem.service.DesignationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designations")
public class DesignationController {

    private final DesignationService designationService;

    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Designation> createDesignation(
            @RequestBody Designation designation) {

        Designation savedDesignation =
                designationService.createDesignation(designation);

        return new ResponseEntity<>(
                savedDesignation,
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Designation>> getAllDesignations() {

        return ResponseEntity.ok(
                designationService.getAllDesignations()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Designation> getDesignationById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                designationService.getDesignationById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Designation> updateDesignation(
            @PathVariable Integer id,
            @RequestBody Designation designation) {

        return ResponseEntity.ok(
                designationService.updateDesignation(id, designation)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDesignation(
            @PathVariable Integer id) {

        designationService.deleteDesignation(id);

        return ResponseEntity.ok(
                "Designation deleted successfully"
        );
    }

    // SEARCH BY TITLE
    @GetMapping("/search")
    public ResponseEntity<List<Designation>> searchByTitle(
            @RequestParam String title) {

        return ResponseEntity.ok(
                designationService.searchByTitle(title)
        );
    }

    // GET BY EXACT TITLE
    @GetMapping("/title")
    public ResponseEntity<Designation> getDesignationByTitle(
            @RequestParam String title) {

        return ResponseEntity.ok(
                designationService.getDesignationByTitle(title)
        );
    }
}
