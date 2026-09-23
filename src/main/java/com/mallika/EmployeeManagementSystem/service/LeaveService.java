package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Leave;
import com.mallika.EmployeeManagementSystem.model.User;
import com.mallika.EmployeeManagementSystem.repository.LeaveRepository;
import com.mallika.EmployeeManagementSystem.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final UserRepository userRepository;

    public LeaveService(
            LeaveRepository leaveRepository,
            UserRepository userRepository) {

        this.leaveRepository = leaveRepository;
        this.userRepository = userRepository;
    }


    // =========================================================
    // CREATE
    // =========================================================

    public Leave createLeave(Leave leave) {

        return leaveRepository.createLeave(leave);
    }


    // =========================================================
    // GET ALL
    // =========================================================

    public List<Leave> getAllLeaves() {

        return leaveRepository.getAllLeaves();
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    public Leave getLeaveById(Integer id) {

        return leaveRepository
                .getLeaveById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave not found with id: " + id
                        ));
    }


    // =========================================================
    // UPDATE
    // =========================================================

    public Leave updateLeave(
            Integer id,
            Leave leaveDetails) {

        return leaveRepository.updateLeave(
                id,
                leaveDetails
        );
    }


    // =========================================================
    // DELETE
    // =========================================================

    public void deleteLeave(Integer id) {

        boolean deleted =
                leaveRepository.deleteLeave(id);

        if (!deleted) {
            throw new ResourceNotFoundException(
                    "Leave not found with id: " + id
            );
        }
    }


    // =========================================================
    // GET BY EMPLOYEE
    // =========================================================

    public List<Leave> getLeavesByEmployee(
            Integer employeeId) {

        return leaveRepository
                .findByEmployeeEmployeeId(employeeId);
    }


    // =========================================================
    // GET BY STATUS
    // =========================================================

    public List<Leave> getLeavesByStatus(
            String status) {

        return leaveRepository
                .findByStatusIgnoreCase(status);
    }


    // =========================================================
    // GET EMPLOYEE LEAVES BY STATUS
    // =========================================================

    public List<Leave> getEmployeeLeavesByStatus(
            Integer employeeId,
            String status) {

        return leaveRepository
                .findByEmployeeEmployeeIdAndStatusIgnoreCase(
                        employeeId,
                        status
                );
    }


    // =========================================================
    // GET BY FROM DATE
    // =========================================================

    public List<Leave> getLeavesByFromDate(
            LocalDate fromDate) {

        return leaveRepository
                .findByFromDate(fromDate);
    }


    // =========================================================
    // GET BY TO DATE
    // =========================================================

    public List<Leave> getLeavesByToDate(
            LocalDate toDate) {

        return leaveRepository
                .findByToDate(toDate);
    }


    // =========================================================
    // GET LEAVES BETWEEN DATES
    // =========================================================

    public List<Leave> getLeavesBetweenDates(
            LocalDate startDate,
            LocalDate endDate) {

        return leaveRepository
                .findByFromDateBetween(
                        startDate,
                        endDate
                );
    }


    // =========================================================
    // GET LOGGED-IN EMPLOYEE'S LEAVES
    // =========================================================

    public List<Leave> getMyLeaves() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found"
                                ));

        if (user.getEmployee() == null) {
            throw new ResourceNotFoundException(
                    "Employee not found"
            );
        }

        Integer employeeId =
                user.getEmployee().getEmployeeId();

        return leaveRepository
                .findByEmployeeEmployeeId(employeeId);
    }
}