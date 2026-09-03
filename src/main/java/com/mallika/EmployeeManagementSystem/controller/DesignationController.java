package com.mallika.EmployeeManagementSystem.controller;

import com.mallika.EmployeeManagementSystem.model.Designation;
import com.mallika.EmployeeManagementSystem.service.DesignationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/designations")
public class DesignationController {

    @Autowired
    DesignationService designationService;

    @PostMapping
    public Designation addDesignation(@Valid @RequestBody Designation designation){
        return designationService.save(designation);
    }

    @GetMapping("/{id}")
    public Designation getDesignationById(@PathVariable Long id){
        return designationService.getById(id);
    }
}
