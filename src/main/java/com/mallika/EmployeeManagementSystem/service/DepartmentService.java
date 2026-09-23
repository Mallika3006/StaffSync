package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Department;
import com.mallika.EmployeeManagementSystem.model.Team;
import com.mallika.EmployeeManagementSystem.model.User;
import com.mallika.EmployeeManagementSystem.repository.DepartmentRepository;
import com.mallika.EmployeeManagementSystem.repository.TeamRepository;
import com.mallika.EmployeeManagementSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public DepartmentService(
            DepartmentRepository departmentRepository,
            TeamRepository teamRepository,
            UserRepository userRepository) {

        this.departmentRepository = departmentRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public Department createDepartment(Department department) {
        return departmentRepository.createDepartment(department);
    }

    // GET ALL
    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    // GET BY ID
    public Department getDepartmentById(Integer id) {

        return departmentRepository.getDepartmentById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + id
                        ));
    }

    // UPDATE
    public Department updateDepartment(
            Integer id,
            Department departmentDetails) {

        getDepartmentById(id);

        Department updated =
                departmentRepository.updateDepartment(
                        id,
                        departmentDetails
                );

        if (updated == null) {
            throw new ResourceNotFoundException(
                    "Department not found with id: " + id
            );
        }

        return updated;
    }

    // DELETE
    public void deleteDepartment(Integer id) {

        getDepartmentById(id);

        boolean deleted =
                departmentRepository.deleteDepartment(id);

        if (!deleted) {
            throw new ResourceNotFoundException(
                    "Department not found with id: " + id
            );
        }
    }

    // SEARCH BY NAME
    public List<Department> searchByName(String name) {

        return departmentRepository
                .findByDepartmentNameContainingIgnoreCase(name);
    }

    // GET BY EXACT NAME
    public Department getDepartmentByName(String name) {

        return departmentRepository
                .findByDepartmentNameIgnoreCase(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with name: " + name
                        ));
    }

    // SEARCH BY LOCATION
    public List<Department> getDepartmentsByLocation(
            String location) {

        return departmentRepository
                .findByLocationContainingIgnoreCase(location);
    }

    // GET EMPLOYEE'S OWN DEPARTMENT
    public Department getDepartmentByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with username: "
                                        + username
                        ));

        if (user.getEmployee() == null) {
            throw new ResourceNotFoundException(
                    "Employee not assigned to this user"
            );
        }

        Integer employeeId =
                user.getEmployee().getEmployeeId();

        Team team =
                teamRepository.findByEmployeesEmployeeId(employeeId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Team not found for employee id: "
                                                + employeeId
                                ));

        if (team.getDepartment() == null) {
            throw new ResourceNotFoundException(
                    "Department not assigned to this team"
            );
        }

        return team.getDepartment();
    }
}