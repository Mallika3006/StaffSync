package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Attendance;
import com.mallika.EmployeeManagementSystem.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // CREATE
    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    // GET ALL
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // GET BY ID
    public Attendance getAttendanceById(Integer id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found with id: " + id
                        ));
    }

    // UPDATE
    public Attendance updateAttendance(
            Integer id,
            Attendance attendanceDetails) {

        Attendance attendance = getAttendanceById(id);

        attendance.setAttDate(attendanceDetails.getAttDate());
        attendance.setStatus(attendanceDetails.getStatus());
        attendance.setCheckInTime(attendanceDetails.getCheckInTime());
        attendance.setCheckOutTime(attendanceDetails.getCheckOutTime());
        attendance.setEmployee(attendanceDetails.getEmployee());

        return attendanceRepository.save(attendance);
    }

    // DELETE
    public void deleteAttendance(Integer id) {

        Attendance attendance = getAttendanceById(id);

        attendanceRepository.delete(attendance);
    }

    // GET BY EMPLOYEE
    public List<Attendance> getAttendanceByEmployee(Integer employeeId) {

        return attendanceRepository
                .findByEmployeeEmployeeId(employeeId);
    }

    // GET BY DATE
    public List<Attendance> getAttendanceByDate(LocalDate date) {

        return attendanceRepository.findByAttDate(date);
    }

    // GET BY EMPLOYEE AND DATE
    public List<Attendance> getEmployeeAttendanceByDate(
            Integer employeeId,
            LocalDate date) {

        return attendanceRepository
                .findByEmployeeEmployeeIdAndAttDate(
                        employeeId,
                        date
                );
    }

    // GET BY STATUS
    public List<Attendance> getAttendanceByStatus(String status) {

        return attendanceRepository
                .findByStatusIgnoreCase(status);
    }

    // GET EMPLOYEE ATTENDANCE BY STATUS
    public List<Attendance> getEmployeeAttendanceByStatus(
            Integer employeeId,
            String status) {

        return attendanceRepository
                .findByEmployeeEmployeeIdAndStatusIgnoreCase(
                        employeeId,
                        status
                );
    }
}
