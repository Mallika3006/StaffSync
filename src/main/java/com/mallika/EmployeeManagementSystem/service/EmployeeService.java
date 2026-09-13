package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Employee;
import com.mallika.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // CREATE
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // GET ALL
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // GET BY ID
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id
                        ));
    }

    // UPDATE
    public Employee updateEmployee(Integer id, Employee employeeDetails) {

        Employee employee = getEmployeeById(id);

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setDateOfBirth(employeeDetails.getDateOfBirth());
        employee.setHireDate(employeeDetails.getHireDate());
        employee.setAddress(employeeDetails.getAddress());
        employee.setDesignation(employeeDetails.getDesignation());
        employee.setTeam(employeeDetails.getTeam());

        return employeeRepository.save(employee);
    }

    // DELETE
    public void deleteEmployee(Integer id) {

        Employee employee = getEmployeeById(id);

        employeeRepository.delete(employee);
    }

    // SEARCH BY FIRST NAME
    public List<Employee> searchByFirstName(String firstName) {
        return employeeRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    // SEARCH BY LAST NAME
    public List<Employee> searchByLastName(String lastName) {
        return employeeRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    // SEARCH BY NAME
    public List<Employee> searchByName(String name) {
        return employeeRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        name, name
                );
    }

    // SEARCH BY EMAIL
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with email: " + email
                        ));
    }
    // FILTER BY DESIGNATION
    public List<Employee> getEmployeesByDesignation(Integer designationId) {
        return employeeRepository.findByDesignationDesignationId(designationId);
    }

    // FILTER BY TEAM
    public List<Employee> getEmployeesByTeam(Integer teamId) {
        return employeeRepository.findByTeamTeamId(teamId);
    }

    // SORT
    public List<Employee> getEmployeesSorted(String field, String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(field).descending();
        } else {
            sort = Sort.by(field).ascending();
        }

        return employeeRepository.findAll(sort);
    }
}