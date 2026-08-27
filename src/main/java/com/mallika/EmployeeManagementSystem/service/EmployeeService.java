package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.model.Employee;
import com.mallika.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployeeById(Integer id, Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setFirst_name(employee.getFirst_name());
        existing.setLast_name(employee.getLast_name());
        existing.setEmail(employee.getEmail());
        existing.setPhone(employee.getPhone());
        existing.setAddress(employee.getAddress());
        existing.setD_o_b(employee.getD_o_b());
        existing.setHire_date(employee.getHire_date());

        return employeeRepository.save(existing);
    }

    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }
}
