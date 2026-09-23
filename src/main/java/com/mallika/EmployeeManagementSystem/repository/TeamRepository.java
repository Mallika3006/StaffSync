package com.mallika.EmployeeManagementSystem.repository;

import com.mallika.EmployeeManagementSystem.model.Department;
import com.mallika.EmployeeManagementSystem.model.Employee;
import com.mallika.EmployeeManagementSystem.model.Team;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepository {

    private final DataSource dataSource;

    public TeamRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // CREATE
    public Team createTeam(Team team) {

        String sql = "SELECT * FROM create_team(?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, team.getTeamName());
            statement.setString(2, team.getDescription());

            if (team.getDepartment() != null) {
                statement.setInt(
                        3,
                        team.getDepartment().getDepartmentId()
                );
            } else {
                statement.setNull(3, Types.INTEGER);
            }

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapTeam(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error creating team", e);
        }
    }

    // GET ALL
    public List<Team> getAllTeams() {

        String sql = "SELECT * FROM get_all_teams()";

        List<Team> teams = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                teams.add(mapTeam(rs));
            }

            return teams;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching teams", e);
        }
    }

    // GET BY ID
    public Optional<Team> getTeamById(Integer id) {

        String sql = "SELECT * FROM get_team_by_id(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapTeam(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching team by id", e);
        }
    }

    // UPDATE
    public Team updateTeam(Integer id, Team team) {

        String sql = "SELECT * FROM update_team(?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);
            statement.setString(2, team.getTeamName());
            statement.setString(3, team.getDescription());

            if (team.getDepartment() != null) {
                statement.setInt(
                        4,
                        team.getDepartment().getDepartmentId()
                );
            } else {
                statement.setNull(4, Types.INTEGER);
            }

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapTeam(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating team", e);
        }
    }

    // DELETE
    public boolean deleteTeam(Integer id) {

        String sql = "SELECT delete_team(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            return rs.next() && rs.getBoolean(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting team", e);
        }
    }

    // GET BY NAME
    public Optional<Team> findByTeamNameIgnoreCase(
            String teamName) {

        String sql = "SELECT * FROM get_team_by_name(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, teamName);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapTeam(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching team by name", e);
        }
    }

    // SEARCH BY NAME
    public List<Team> findByTeamNameContainingIgnoreCase(
            String teamName) {

        String sql = "SELECT * FROM search_teams_by_name(?)";

        List<Team> teams = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setString(1, teamName);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                teams.add(mapTeam(rs));
            }

            return teams;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error searching teams", e);
        }
    }

    // GET TEAMS BY DEPARTMENT
    public List<Team> findByDepartmentDepartmentId(
            Integer departmentId) {

        String sql = "SELECT * FROM get_teams_by_department(?)";

        List<Team> teams = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, departmentId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                teams.add(mapTeam(rs));
            }

            return teams;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching teams by department", e);
        }
    }

    // GET TEAM BY EMPLOYEE
    public Optional<Team> findByEmployeesEmployeeId(
            Integer employeeId) {

        String sql = "SELECT * FROM get_team_by_employee(?)";

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, employeeId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapTeam(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching team for employee id: "
                            + employeeId, e);
        }
    }

    // GET TEAM MEMBERS
    public List<Employee> getTeamMembersByEmployeeId(
            Integer employeeId) {

        String sql =
                "SELECT * FROM get_team_members_by_employee(?)";

        List<Employee> employees = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(sql)) {

            statement.setInt(1, employeeId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                employees.add(mapEmployee(rs));
            }

            return employees;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error fetching team members", e);
        }
    }

    // MAP TEAM
    private Team mapTeam(ResultSet rs)
            throws SQLException {

        Team team = new Team();

        team.setTeamId(
                rs.getInt("team_id"));

        team.setTeamName(
                rs.getString("team_name"));

        team.setDescription(
                rs.getString("description"));

        int departmentId =
                rs.getInt("department_id");

        if (!rs.wasNull()) {

            Department department =
                    new Department();

            department.setDepartmentId(
                    departmentId);

            team.setDepartment(department);
        }

        return team;
    }

    // MAP EMPLOYEE
    private Employee mapEmployee(ResultSet rs)
            throws SQLException {

        Employee employee =
                new Employee();

        employee.setEmployeeId(
                rs.getInt("employee_id"));

        employee.setFirstName(
                rs.getString("first_name"));

        employee.setLastName(
                rs.getString("last_name"));

        employee.setEmail(
                rs.getString("email"));

        employee.setPhone(
                rs.getString("phone"));

        Date hireDate =
                rs.getDate("hire_date");

        if (hireDate != null) {
            employee.setHireDate(
                    hireDate.toLocalDate());
        }

        int teamId =
                rs.getInt("team_id");

        if (!rs.wasNull()) {

            Team team = new Team();

            team.setTeamId(teamId);

            employee.setTeam(team);
        }

        return employee;
    }
}