package com.mallika.EmployeeManagementSystem.controller;

import com.mallika.EmployeeManagementSystem.model.Team;
import com.mallika.EmployeeManagementSystem.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Team> createTeam(
            @RequestBody Team team) {

        Team savedTeam = teamService.createTeam(team);

        return new ResponseEntity<>(
                savedTeam,
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {

        return ResponseEntity.ok(
                teamService.getAllTeams()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                teamService.getTeamById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(
            @PathVariable Integer id,
            @RequestBody Team team) {

        return ResponseEntity.ok(
                teamService.updateTeam(id, team)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(
            @PathVariable Integer id) {

        teamService.deleteTeam(id);

        return ResponseEntity.ok(
                "Team deleted successfully"
        );
    }

    // SEARCH BY TEAM NAME
    @GetMapping("/search")
    public ResponseEntity<List<Team>> searchByTeamName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                teamService.searchByTeamName(name)
        );
    }

    // GET BY EXACT NAME
    @GetMapping("/name")
    public ResponseEntity<Team> getTeamByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                teamService.getTeamByName(name)
        );
    }

    // GET TEAMS BY DEPARTMENT
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Team>> getTeamsByDepartment(
            @PathVariable Integer departmentId) {

        return ResponseEntity.ok(
                teamService.getTeamsByDepartment(departmentId)
        );
    }
}
