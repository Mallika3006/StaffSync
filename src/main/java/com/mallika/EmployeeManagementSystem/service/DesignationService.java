package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Designation;
import com.mallika.EmployeeManagementSystem.repository.DesignationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignationService {

    private final DesignationRepository designationRepository;

    public DesignationService(DesignationRepository designationRepository) {
        this.designationRepository = designationRepository;
    }

    // CREATE
    public Designation createDesignation(Designation designation) {
        return designationRepository.save(designation);
    }

    // GET ALL
    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

    // GET BY ID
    public Designation getDesignationById(Integer id) {
        return designationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Designation not found with id: " + id
                        ));
    }

    // UPDATE
    public Designation updateDesignation(
            Integer id,
            Designation designationDetails) {

        Designation designation = getDesignationById(id);

        designation.setDesignationTitle(
                designationDetails.getDesignationTitle()
        );

        designation.setMinSalary(
                designationDetails.getMinSalary()
        );

        designation.setMaxSalary(
                designationDetails.getMaxSalary()
        );

        return designationRepository.save(designation);
    }

    // DELETE
    public void deleteDesignation(Integer id) {

        Designation designation = getDesignationById(id);

        designationRepository.delete(designation);
    }

    // SEARCH BY TITLE
    public List<Designation> searchByTitle(String title) {
        return designationRepository
                .findByDesignationTitleContainingIgnoreCase(title);
    }

    // GET BY EXACT TITLE
    public Designation getDesignationByTitle(String title) {

        return designationRepository
                .findByDesignationTitleIgnoreCase(title)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Designation not found with title: " + title
                        ));
    }
}