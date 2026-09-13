package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> findByEmployeeEmployeeId(Integer employeeId);

    List<Leave> findByStatusIgnoreCase(String status);

    List<Leave> findByEmployeeEmployeeIdAndStatusIgnoreCase(
            Integer employeeId,
            String status
    );

    List<Leave> findByFromDate(LocalDate fromDate);

    List<Leave> findByToDate(LocalDate toDate);

    List<Leave> findByFromDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}
