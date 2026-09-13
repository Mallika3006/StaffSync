package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Team;
import com.mallika.EmployeeManagementSystem.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // CREATE
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    // GET ALL
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // GET BY ID
    public Team getTeamById(Integer id) {
        return teamRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Team not found with id: " + id
                        ));
    }

    // UPDATE
    public Team updateTeam(Integer id, Team teamDetails) {

        Team team = getTeamById(id);

        team.setTeamName(teamDetails.getTeamName());
        team.setDescription(teamDetails.getDescription());
        team.setDepartment(teamDetails.getDepartment());
        team.setProjects(teamDetails.getProjects());

        return teamRepository.save(team);
    }

    // DELETE
    public void deleteTeam(Integer id) {

        Team team = getTeamById(id);

        teamRepository.delete(team);
    }

    // SEARCH BY TEAM NAME
    public List<Team> searchByTeamName(String teamName) {
        return teamRepository
                .findByTeamNameContainingIgnoreCase(teamName);
    }

    // GET BY EXACT TEAM NAME
    public Team getTeamByName(String teamName) {

        return teamRepository
                .findByTeamNameIgnoreCase(teamName)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Team not found with name: " + teamName
                        ));
    }

    // GET TEAMS BY DEPARTMENT
    public List<Team> getTeamsByDepartment(Integer departmentId) {
        return teamRepository
                .findByDepartmentDepartmentId(departmentId);
    }
}
