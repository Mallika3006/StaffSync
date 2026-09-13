package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByEmployeeEmployeeId(Integer employeeId);

    List<Attendance> findByAttDate(LocalDate attDate);

    List<Attendance> findByEmployeeEmployeeIdAndAttDate(
            Integer employeeId,
            LocalDate attDate
    );

    List<Attendance> findByStatusIgnoreCase(String status);

    List<Attendance> findByEmployeeEmployeeIdAndStatusIgnoreCase(
            Integer employeeId,
            String status
    );
}