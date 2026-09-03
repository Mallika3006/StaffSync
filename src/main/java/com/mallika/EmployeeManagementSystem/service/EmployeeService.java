package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.dto.EmployeeRequestDTO;
import com.mallika.EmployeeManagementSystem.dto.EmployeeResponseDTO;
import com.mallika.EmployeeManagementSystem.exception.EmployeeNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Employee;
import com.mallika.EmployeeManagementSystem.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee save(EmployeeRequestDTO dto) {

        Employee employee = new Employee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setHireDate(dto.getHireDate());
        employee.setAddress(dto.getAddress());

        return employeeRepository.save(employee);
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id
                        ));
    }

    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(employee -> {

                    EmployeeResponseDTO dto = new EmployeeResponseDTO();

                    dto.setEmployeeId(
                            employee.getEmployeeId() != null
                                    ? employee.getEmployeeId().longValue()
                                    : null
                    );

                    dto.setFirstName(employee.getFirstName());
                    dto.setLastName(employee.getLastName());
                    dto.setEmail(employee.getEmail());
                    dto.setPhone(employee.getPhone());
                    dto.setDateOfBirth(employee.getDateOfBirth());
                    dto.setHireDate(employee.getHireDate());
                    dto.setAddress(employee.getAddress());

                    if (employee.getDesignation() != null) {
                        dto.setDesignationId(
                                employee.getDesignation()
                                        .getDesignationId()
                                        .longValue()
                        );
                    }

                    if (employee.getTeam() != null) {
                        dto.setTeamId(
                                employee.getTeam()
                                        .getTeamId()
                                        .longValue()
                        );
                    }

                    return dto;
                })
                .toList();
    }

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {

        Employee employee = new Employee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setHireDate(dto.getHireDate());
        employee.setAddress(dto.getAddress());

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeResponseDTO response = new EmployeeResponseDTO();

        response.setEmployeeId(savedEmployee.getEmployeeId().longValue());
        response.setFirstName(savedEmployee.getFirstName());
        response.setLastName(savedEmployee.getLastName());
        response.setEmail(savedEmployee.getEmail());
        response.setPhone(savedEmployee.getPhone());
        response.setDateOfBirth(savedEmployee.getDateOfBirth());
        response.setHireDate(savedEmployee.getHireDate());
        response.setAddress(savedEmployee.getAddress());

        return response;
    }
    public void deleteEmployee(Integer id) {

        Employee employee = employeeRepository.findById(Long.valueOf(id))
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }

    public EmployeeResponseDTO updateEmployee(Integer id, EmployeeRequestDTO dto) {

        Employee employee = employeeRepository.findById(Long.valueOf(id))
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setHireDate(dto.getHireDate());
        employee.setAddress(dto.getAddress());

        Employee updatedEmployee = employeeRepository.save(employee);

        EmployeeResponseDTO response = new EmployeeResponseDTO();

        response.setEmployeeId(updatedEmployee.getEmployeeId().longValue());
        response.setFirstName(updatedEmployee.getFirstName());
        response.setLastName(updatedEmployee.getLastName());
        response.setEmail(updatedEmployee.getEmail());
        response.setPhone(updatedEmployee.getPhone());
        response.setDateOfBirth(updatedEmployee.getDateOfBirth());
        response.setHireDate(updatedEmployee.getHireDate());
        response.setAddress(updatedEmployee.getAddress());

        return response;
    }
}
