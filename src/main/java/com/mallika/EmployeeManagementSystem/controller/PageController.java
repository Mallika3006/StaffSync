package com.mallika.EmployeeManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/admin-login")
    public String adminLogin() {
        return "login/admin-login";
    }

    @GetMapping("/employee-login")
    public String employeeLogin() {
        return "login/employee-login";
    }

    @GetMapping("/employee-dashboard")
    public String employeeDashboard() {
        return "dashboard/employee";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "dashboard/admin";
    }

    @GetMapping("/hr-dashboard")
    public String hrDashboard() {
        return "dashboard/hr";
    }

    @GetMapping("/manager-dashboard")
    public String managerDashboard() {
        return "dashboard/manager";
    }

    @GetMapping("/my-profile")
    public String myProfile() {
        return "employee/my-profile";
    }

    @GetMapping("/my-attendance")
    public String myAttendance() {
        return "employee/my-attendance";
    }

    @GetMapping("/my-leaves")
    public String myLeaves() {
        return "employee/my-leaves";
    }

    @GetMapping("/my-payroll")
    public String myPayroll() {
        return "employee/my-payroll";
    }

    @GetMapping("/my-projects")
    public String myProjects() {
        return "employee/my-projects";
    }

    @GetMapping("/my-tasks")
    public String myTasks() {
        return "employee/my-tasks";
    }

    @GetMapping("/my-team")
    public String myTeam() {
        return "employee/my-team";
    }

    @GetMapping("/my-department")
    public String myDepartment() {
        return "employee/my-department";
    }
}
