package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Designation;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DesignationRepository {

    private final DataSource dataSource;

    public DesignationRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // CREATE
    public Designation createDesignation(Designation designation) {

        String sql = "SELECT * FROM create_designation(?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, designation.getDesignationTitle());
            statement.setBigDecimal(2, designation.getMinSalary());
            statement.setBigDecimal(3, designation.getMaxSalary());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapDesignation(rs);
            }

            throw new RuntimeException("Failed to create designation");

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error creating designation: " + e.getMessage(), e
            );
        }
    }


    // GET ALL
    public List<Designation> getAllDesignations() {

        String sql = "SELECT * FROM get_all_designations()";

        List<Designation> designations = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                designations.add(mapDesignation(rs));
            }

            return designations;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching all designations: " + e.getMessage(), e
            );
        }
    }


    // GET BY ID
    public Optional<Designation> getDesignationById(Integer id) {

        String sql = "SELECT * FROM get_designation_by_id(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapDesignation(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching designation by id: " + e.getMessage(), e
            );
        }
    }


    // UPDATE
    public Designation updateDesignation(
            Integer id,
            Designation designation) {

        String sql = "SELECT * FROM update_designation(?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);
            statement.setString(2, designation.getDesignationTitle());
            statement.setBigDecimal(3, designation.getMinSalary());
            statement.setBigDecimal(4, designation.getMaxSalary());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapDesignation(rs);
            }

            throw new RuntimeException(
                    "Designation not found with id: " + id
            );

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error updating designation: " + e.getMessage(), e
            );
        }
    }


    // DELETE
    public void deleteDesignation(Integer id) {

        String sql = "SELECT delete_designation(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                boolean deleted = rs.getBoolean(1);

                if (!deleted) {
                    throw new RuntimeException(
                            "Designation not found with id: " + id
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error deleting designation: " + e.getMessage(), e
            );
        }
    }


    // SEARCH BY TITLE
    public List<Designation> searchByTitle(String title) {

        String sql = "SELECT * FROM search_designations_by_title(?)";

        List<Designation> designations = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, title);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                designations.add(mapDesignation(rs));
            }

            return designations;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error searching designations: " + e.getMessage(), e
            );
        }
    }


    // GET BY EXACT TITLE
    public Optional<Designation> getDesignationByTitle(String title) {

        String sql = "SELECT * FROM get_designation_by_title(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, title);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapDesignation(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching designation by title: "
                            + e.getMessage(), e
            );
        }
    }


    // MAPPER
    private Designation mapDesignation(ResultSet rs) throws SQLException {

        Designation designation = new Designation();

        designation.setDesignationId(
                rs.getInt("designation_id")
        );

        designation.setDesignationTitle(
                rs.getString("designation_title")
        );

        BigDecimal minSalary =
                rs.getBigDecimal("min_salary");

        BigDecimal maxSalary =
                rs.getBigDecimal("max_salary");

        designation.setMinSalary(minSalary);
        designation.setMaxSalary(maxSalary);

        return designation;
    }
}