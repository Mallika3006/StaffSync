package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.model.Designation;
import com.mallika.EmployeeManagementSystem.repository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DesignationService {

    @Autowired
    DesignationRepository designationRepository;

    public Designation save(Designation designation){
        return designationRepository.save(designation);
    }

    public Designation getById(Long id) {
        return designationRepository.getById(id);
    }
}
