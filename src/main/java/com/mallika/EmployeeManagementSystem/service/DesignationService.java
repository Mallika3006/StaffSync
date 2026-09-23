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

        return designationRepository.createDesignation(designation);
    }


    // GET ALL
    public List<Designation> getAllDesignations() {

        return designationRepository.getAllDesignations();
    }


    // GET BY ID
    public Designation getDesignationById(Integer id) {

        return designationRepository.getDesignationById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Designation not found with id: " + id
                        )
                );
    }


    // UPDATE
    public Designation updateDesignation(
            Integer id,
            Designation designationDetails) {

        // Check whether designation exists
        getDesignationById(id);

        return designationRepository.updateDesignation(
                id,
                designationDetails
        );
    }


    // DELETE
    public void deleteDesignation(Integer id) {

        // Check whether designation exists
        getDesignationById(id);

        designationRepository.deleteDesignation(id);
    }


    // SEARCH BY TITLE
    public List<Designation> searchByTitle(String title) {

        return designationRepository.searchByTitle(title);
    }


    // GET BY EXACT TITLE
    public Designation getDesignationByTitle(String title) {

        return designationRepository
                .getDesignationByTitle(title)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Designation not found with title: " + title
                        )
                );
    }
}