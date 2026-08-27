package com.mallika.EmployeeManagementSystem.controller;


import com.mallika.EmployeeManagementSystem.model.Employee;
import com.mallika.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable Integer id,@RequestBody Employee employee){
        return employeeService.updateEmployeeById(id,employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Integer id){
        employeeService.deleteEmployeeById(id);
    }

}
