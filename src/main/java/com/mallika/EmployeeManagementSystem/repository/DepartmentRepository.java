package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Department;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository {

    private final DataSource dataSource;

    public DepartmentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // CREATE
    public Department createDepartment(Department department) {

        String sql = "SELECT * FROM create_department(?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, department.getDepartmentName());
            statement.setString(2, department.getLocation());
            statement.setString(3, department.getDescription());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapDepartment(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error creating department", e);
        }
    }

    // GET ALL
    public List<Department> getAllDepartments() {

        String sql = "SELECT * FROM get_all_departments()";

        List<Department> departments = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                departments.add(mapDepartment(rs));
            }

            return departments;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching departments", e);
        }
    }

    // GET BY ID
    public Optional<Department> getDepartmentById(Integer id) {

        String sql = "SELECT * FROM get_department_by_id(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapDepartment(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching department by id", e);
        }
    }

    // UPDATE
    public Department updateDepartment(
            Integer id,
            Department department) {

        String sql = "SELECT * FROM update_department(?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);
            statement.setString(2, department.getDepartmentName());
            statement.setString(3, department.getLocation());
            statement.setString(4, department.getDescription());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapDepartment(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating department", e);
        }
    }

    // DELETE
    public boolean deleteDepartment(Integer id) {

        String sql = "SELECT delete_department(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            return rs.next() && rs.getBoolean(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting department", e);
        }
    }

    // SEARCH BY NAME
    public List<Department> findByDepartmentNameContainingIgnoreCase(
            String name) {

        String sql = "SELECT * FROM search_departments_by_name(?)";

        List<Department> departments = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                departments.add(mapDepartment(rs));
            }

            return departments;

        } catch (SQLException e) {
            throw new RuntimeException("Error searching departments", e);
        }
    }

    // GET BY EXACT NAME
    public Optional<Department> findByDepartmentNameIgnoreCase(
            String name) {

        String sql = "SELECT * FROM get_department_by_name(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapDepartment(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching department by name", e);
        }
    }

    // SEARCH BY LOCATION
    public List<Department> findByLocationContainingIgnoreCase(
            String location) {

        String sql = "SELECT * FROM search_departments_by_location(?)";

        List<Department> departments = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, location);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                departments.add(mapDepartment(rs));
            }

            return departments;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error searching departments by location", e);
        }
    }

    // MAPPER
    private Department mapDepartment(ResultSet rs)
            throws SQLException {

        Department department = new Department();

        department.setDepartmentId(
                rs.getInt("department_id"));

        department.setDepartmentName(
                rs.getString("department_name"));

        department.setLocation(
                rs.getString("location"));

        department.setDescription(
                rs.getString("description"));

        return department;
    }
}