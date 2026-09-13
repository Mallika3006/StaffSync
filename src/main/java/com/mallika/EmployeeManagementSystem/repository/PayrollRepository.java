package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Integer> {

    List<Payroll> findByEmployeeEmployeeId(Integer employeeId);

    List<Payroll> findByPayMonthIgnoreCase(String payMonth);

    List<Payroll> findByEmployeeEmployeeIdAndPayMonthIgnoreCase(
            Integer employeeId,
            String payMonth
    );

    List<Payroll> findByPaymentDate(LocalDate paymentDate);

    List<Payroll> findByEmployeeEmployeeIdAndPaymentDate(
            Integer employeeId,
            LocalDate paymentDate
    );
}