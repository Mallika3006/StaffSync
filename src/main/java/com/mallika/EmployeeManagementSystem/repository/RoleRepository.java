package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Role;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository {

    private final DataSource dataSource;

    public RoleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    // CREATE
    public Role createRole(Role role) {

        String sql = "SELECT * FROM create_role(?, ?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, role.getRoleName());
            statement.setString(2, role.getDescription());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapRole(rs);
            }

            throw new RuntimeException("Failed to create role");

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error creating role: " + e.getMessage(), e
            );
        }
    }


    // GET ALL
    public List<Role> getAllRoles() {

        String sql = "SELECT * FROM get_all_roles()";

        List<Role> roles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                roles.add(mapRole(rs));
            }

            return roles;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching all roles: " + e.getMessage(), e
            );
        }
    }


    // GET BY ID
    public Optional<Role> getRoleById(Integer id) {

        String sql = "SELECT * FROM get_role_by_id(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRole(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching role by id: " + e.getMessage(), e
            );
        }
    }


    // UPDATE
    public Role updateRole(Integer id, Role role) {

        String sql = "SELECT * FROM update_role(?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);
            statement.setString(2, role.getRoleName());
            statement.setString(3, role.getDescription());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapRole(rs);
            }

            throw new RuntimeException(
                    "Role not found with id: " + id
            );

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error updating role: " + e.getMessage(), e
            );
        }
    }


    // DELETE
    public void deleteRole(Integer id) {

        String sql = "SELECT delete_role(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                boolean deleted = rs.getBoolean(1);

                if (!deleted) {
                    throw new RuntimeException(
                            "Role not found with id: " + id
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error deleting role: " + e.getMessage(), e
            );
        }
    }


    // SEARCH BY ROLE NAME
    public List<Role> searchByRoleName(String roleName) {

        String sql = "SELECT * FROM search_roles_by_name(?)";

        List<Role> roles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, roleName);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                roles.add(mapRole(rs));
            }

            return roles;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error searching roles: " + e.getMessage(), e
            );
        }
    }


    // EXACT ROLE NAME
    public Optional<Role> getByExactRoleName(String roleName) {

        String sql = "SELECT * FROM get_role_by_exact_name(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, roleName);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRole(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching role by name: " + e.getMessage(), e
            );
        }
    }


    // SORT
    public List<Role> sortRoles(
            String field,
            String direction) {

        String sql = "SELECT * FROM sort_roles(?, ?)";

        List<Role> roles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, field);
            statement.setString(2, direction);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                roles.add(mapRole(rs));
            }

            return roles;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error sorting roles: " + e.getMessage(), e
            );
        }
    }


    // MAPPER
    private Role mapRole(ResultSet rs) throws SQLException {

        Role role = new Role();

        role.setRoleId(
                rs.getInt("role_id")
        );

        role.setRoleName(
                rs.getString("role_name")
        );

        role.setDescription(
                rs.getString("description")
        );

        return role;
    }
}