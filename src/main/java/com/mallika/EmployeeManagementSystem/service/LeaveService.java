package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Leave;
import com.mallika.EmployeeManagementSystem.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    // CREATE
    public Leave createLeave(Leave leave) {
        return leaveRepository.save(leave);
    }

    // GET ALL
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    // GET BY ID
    public Leave getLeaveById(Integer id) {
        return leaveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave not found with id: " + id
                        ));
    }

    // UPDATE
    public Leave updateLeave(
            Integer id,
            Leave leaveDetails) {

        Leave leave = getLeaveById(id);

        leave.setFromDate(leaveDetails.getFromDate());
        leave.setToDate(leaveDetails.getToDate());
        leave.setReason(leaveDetails.getReason());
        leave.setStatus(leaveDetails.getStatus());
        leave.setEmployee(leaveDetails.getEmployee());

        return leaveRepository.save(leave);
    }

    // DELETE
    public void deleteLeave(Integer id) {

        Leave leave = getLeaveById(id);

        leaveRepository.delete(leave);
    }

    // GET BY EMPLOYEE
    public List<Leave> getLeavesByEmployee(Integer employeeId) {

        return leaveRepository
                .findByEmployeeEmployeeId(employeeId);
    }

    // GET BY STATUS
    public List<Leave> getLeavesByStatus(String status) {

        return leaveRepository
                .findByStatusIgnoreCase(status);
    }

    // GET EMPLOYEE LEAVES BY STATUS
    public List<Leave> getEmployeeLeavesByStatus(
            Integer employeeId,
            String status) {

        return leaveRepository
                .findByEmployeeEmployeeIdAndStatusIgnoreCase(
                        employeeId,
                        status
                );
    }

    // GET BY FROM DATE
    public List<Leave> getLeavesByFromDate(LocalDate fromDate) {

        return leaveRepository.findByFromDate(fromDate);
    }

    // GET BY TO DATE
    public List<Leave> getLeavesByToDate(LocalDate toDate) {

        return leaveRepository.findByToDate(toDate);
    }

    // GET LEAVES BETWEEN DATES
    public List<Leave> getLeavesBetweenDates(
            LocalDate startDate,
            LocalDate endDate) {

        return leaveRepository.findByFromDateBetween(
                startDate,
                endDate
        );
    }
}
