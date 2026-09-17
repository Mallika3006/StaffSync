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
        return departmentRepository.save(department);
    }

    // GET ALL
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // GET BY ID
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + id
                        ));
    }

    // UPDATE
    public Department updateDepartment(
            Integer id,
            Department departmentDetails) {

        Department department = getDepartmentById(id);

        department.setDepartmentName(
                departmentDetails.getDepartmentName()
        );

        department.setLocation(
                departmentDetails.getLocation()
        );

        department.setDescription(
                departmentDetails.getDescription()
        );

        return departmentRepository.save(department);
    }

    // DELETE
    public void deleteDepartment(Integer id) {

        Department department = getDepartmentById(id);

        departmentRepository.delete(department);
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
    public List<Department> getDepartmentsByLocation(String location) {

        return departmentRepository
                .findByLocationContainingIgnoreCase(location);
    }

    // GET EMPLOYEE'S OWN DEPARTMENT
    public Department getDepartmentByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with username: " + username
                        ));

        if (user.getEmployee() == null) {
            throw new ResourceNotFoundException(
                    "Employee not assigned to this user"
            );
        }

        Integer employeeId = user.getEmployee().getEmployeeId();

        Team team = teamRepository.findByEmployeesEmployeeId(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Team not found for employee id: " + employeeId
                        ));

        if (team.getDepartment() == null) {
            throw new ResourceNotFoundException(
                    "Department not assigned to this team"
            );
        }

        return team.getDepartment();
    }
}