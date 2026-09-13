package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Project;
import com.mallika.EmployeeManagementSystem.repository.ProjectRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // CREATE
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // GET ALL
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // GET BY ID
    public Project getProjectById(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found with id: " + id
                        ));
    }

    // UPDATE
    public Project updateProject(Integer id, Project projectDetails) {

        Project project = getProjectById(id);

        project.setProjectName(projectDetails.getProjectName());
        project.setStartDate(projectDetails.getStartDate());
        project.setEndDate(projectDetails.getEndDate());

        return projectRepository.save(project);
    }

    // DELETE
    public void deleteProject(Integer id) {

        Project project = getProjectById(id);

        projectRepository.delete(project);
    }

    // SEARCH BY NAME
    public List<Project> searchByProjectName(String projectName) {
        return projectRepository
                .findByProjectNameContainingIgnoreCase(projectName);
    }

    // EXACT NAME
    public List<Project> getByExactProjectName(String projectName) {
        return projectRepository
                .findByProjectNameIgnoreCase(projectName);
    }

    // START DATE
    public List<Project> getByStartDate(LocalDate startDate) {
        return projectRepository.findByStartDate(startDate);
    }

    // END DATE
    public List<Project> getByEndDate(LocalDate endDate) {
        return projectRepository.findByEndDate(endDate);
    }

    // PROJECTS BETWEEN DATES
    public List<Project> getProjectsBetweenDates(
            LocalDate startDate,
            LocalDate endDate) {

        return projectRepository.findByStartDateBetween(
                startDate,
                endDate
        );
    }

    // ONGOING PROJECTS
    public List<Project> getOngoingProjects() {
        return projectRepository.findByEndDateIsNull();
    }

    // SORT
    public List<Project> sortProjects(
            String field,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        return projectRepository.findAll(sort);
    }
}