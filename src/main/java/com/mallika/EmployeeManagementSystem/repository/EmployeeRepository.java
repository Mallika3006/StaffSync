package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.dto.EmployeeUpdateDTO;
import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Employee;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final DataSource dataSource;

    public EmployeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // =========================
    // GET ALL EMPLOYEES
    // =========================

    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        String sql = "{call get_all_employees()}";

        try (
                Connection connection = dataSource.getConnection();
                CallableStatement statement =
                        connection.prepareCall(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                employees.add(mapEmployee(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching employees", e
            );
        }

        return employees;
    }


    // =========================
    // GET EMPLOYEE BY ID
    // =========================

    public Optional<Employee> getEmployeeById(Integer id) {

        String sql = "{call get_employee_by_id(?)}";

        try (
                Connection connection = dataSource.getConnection();
                CallableStatement statement =
                        connection.prepareCall(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(mapEmployee(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching employee with id: " + id,
                    e
            );
        }

        return Optional.empty();
    }


    // =========================
    // SEARCH BY NAME
    // =========================

    public List<Employee> searchByName(String name) {

        List<Employee> employees = new ArrayList<>();

        String sql = "{call search_employee_by_name(?)}";

        try (
                Connection connection = dataSource.getConnection();
                CallableStatement statement =
                        connection.prepareCall(sql)
        ) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    employees.add(mapEmployee(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error searching employees", e
            );
        }

        return employees;
    }


    // =========================
    // SEARCH BY EMAIL
    // =========================

    public Optional<Employee> getEmployeeByEmail(String email) {

        String sql = "{call get_employee_by_email(?)}";

        try (
                Connection connection = dataSource.getConnection();
                CallableStatement statement =
                        connection.prepareCall(sql)
        ) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(mapEmployee(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching employee by email",
                    e
            );
        }

        return Optional.empty();
    }


    // =========================
    // FILTER BY DESIGNATION
    // =========================

    public List<Employee> getEmployeesByDesignation(
            Integer designationId) {

        List<Employee> employees = new ArrayList<>();

        String sql = "{call get_employees_by_designation(?)}";

        try (
                Connection connection = dataSource.getConnection();
                CallableStatement statement =
                        connection.prepareCall(sql)
        ) {

            statement.setInt(1, designationId);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    employees.add(mapEmployee(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching employees by designation",
                    e
            );
        }

        return employees;
    }


    // =========================
    // FILTER BY TEAM
    // =========================

    public List<Employee> getEmployeesByTeam(
            Integer teamId) {

        List<Employee> employees = new ArrayList<>();

        String sql = "{call get_employees_by_team(?)}";

        try (
                Connection connection = dataSource.getConnection();
                CallableStatement statement =
                        connection.prepareCall(sql)
        ) {

            statement.setInt(1, teamId);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    employees.add(mapEmployee(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching employees by team",
                    e
            );
        }

        return employees;
    }


    // =========================
    // MAP RESULTSET TO EMPLOYEE
    // =========================

    private Employee mapEmployee(ResultSet rs)
            throws SQLException {

        Employee employee = new Employee();

        employee.setEmployeeId(
                rs.getInt("employee_id")
        );

        employee.setFirstName(
                rs.getString("first_name")
        );

        employee.setLastName(
                rs.getString("last_name")
        );

        employee.setEmail(
                rs.getString("email")
        );

        employee.setPhone(
                rs.getString("phone")
        );

        if (rs.getDate("date_of_birth") != null) {
            employee.setDateOfBirth(
                    rs.getDate("date_of_birth")
                            .toLocalDate()
            );
        }

        if (rs.getDate("hire_date") != null) {
            employee.setHireDate(
                    rs.getDate("hire_date")
                            .toLocalDate()
            );
        }

        employee.setAddress(
                rs.getString("address")
        );

        employee.setProfilePhoto(
                rs.getString("profile_photo")
        );

        return employee;
    }

    // =========================
// UPDATE MY PROFILE
// =========================

    public Employee updateMyProfile(
            Integer employeeId,
            EmployeeUpdateDTO updateDetails) {

        String sql = "{call update_employee_profile(?, ?, ?, ?, ?, ?, ?)}";

        try (
                Connection connection = dataSource.getConnection();
                CallableStatement statement =
                        connection.prepareCall(sql)
        ) {

            statement.setInt(1, employeeId);
            statement.setString(2, updateDetails.getFirstName());
            statement.setString(3, updateDetails.getLastName());
            statement.setString(4, updateDetails.getEmail());
            statement.setString(5, updateDetails.getPhone());

            if (updateDetails.getDateOfBirth() != null) {
                statement.setDate(
                        6,
                        java.sql.Date.valueOf(
                                updateDetails.getDateOfBirth()
                        )
                );
            } else {
                statement.setNull(6, java.sql.Types.DATE);
            }

            statement.setString(7, updateDetails.getAddress());

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapEmployee(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error updating employee profile",
                    e
            );
        }

        throw new RuntimeException(
                "Employee not found with id: " + employeeId
        );
    }

    // =========================
// UPDATE PROFILE PHOTO
// =========================

    public Employee updateProfilePhoto(
            Integer employeeId,
            String profilePhoto) {

        String sql =
                "{call update_employee_profile_photo(?, ?)}";

        try (
                Connection connection = dataSource.getConnection();
                CallableStatement statement =
                        connection.prepareCall(sql)
        ) {

            statement.setInt(1, employeeId);
            statement.setString(2, profilePhoto);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapEmployee(resultSet);
                }
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error updating profile photo",
                    e
            );
        }

        throw new ResourceNotFoundException(
                "Employee not found with id: " + employeeId
        );
    }
}