package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Payroll;
import com.mallika.EmployeeManagementSystem.model.User;
import com.mallika.EmployeeManagementSystem.repository.PayrollRepository;
import com.mallika.EmployeeManagementSystem.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final UserRepository userRepository;

    public PayrollService(
            PayrollRepository payrollRepository,
            UserRepository userRepository) {

        this.payrollRepository = payrollRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public Payroll createPayroll(Payroll payroll) {
        return payrollRepository.save(payroll);
    }

    // GET ALL
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    // GET BY ID
    public Payroll getPayrollById(Integer id) {
        return payrollRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payroll not found with id: " + id
                        ));
    }

    // UPDATE
    public Payroll updatePayroll(
            Integer id,
            Payroll payrollDetails) {

        Payroll payroll = getPayrollById(id);

        payroll.setPayMonth(payrollDetails.getPayMonth());
        payroll.setBasicSalary(payrollDetails.getBasicSalary());
        payroll.setAllowances(payrollDetails.getAllowances());
        payroll.setDeductions(payrollDetails.getDeductions());
        payroll.setNetSalary(payrollDetails.getNetSalary());
        payroll.setPaymentDate(payrollDetails.getPaymentDate());
        payroll.setEmployee(payrollDetails.getEmployee());

        return payrollRepository.save(payroll);
    }

    // DELETE
    public void deletePayroll(Integer id) {

        Payroll payroll = getPayrollById(id);

        payrollRepository.delete(payroll);
    }

    // GET BY EMPLOYEE
    public List<Payroll> getPayrollsByEmployee(Integer employeeId) {

        return payrollRepository
                .findByEmployeeEmployeeId(employeeId);
    }

    // GET BY MONTH
    public List<Payroll> getPayrollsByMonth(String payMonth) {

        return payrollRepository
                .findByPayMonthIgnoreCase(payMonth);
    }

    // GET BY EMPLOYEE AND MONTH
    public List<Payroll> getEmployeePayrollByMonth(
            Integer employeeId,
            String payMonth) {

        return payrollRepository
                .findByEmployeeEmployeeIdAndPayMonthIgnoreCase(
                        employeeId,
                        payMonth
                );
    }

    // GET BY PAYMENT DATE
    public List<Payroll> getPayrollsByPaymentDate(
            LocalDate paymentDate) {

        return payrollRepository
                .findByPaymentDate(paymentDate);
    }

    // GET BY EMPLOYEE AND PAYMENT DATE
    public List<Payroll> getEmployeePayrollByPaymentDate(
            Integer employeeId,
            LocalDate paymentDate) {

        return payrollRepository
                .findByEmployeeEmployeeIdAndPaymentDate(
                        employeeId,
                        paymentDate
                );
    }

    // GET LOGGED-IN EMPLOYEE'S PAYROLL
    public List<Payroll> getMyPayrolls() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

        Integer employeeId =
                user.getEmployee().getEmployeeId();

        return payrollRepository
                .findByEmployeeEmployeeId(employeeId);
    }
}