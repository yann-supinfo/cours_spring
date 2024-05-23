package org.example.controllers;

import org.example.dto.JsonResponse;
import org.example.dto.Team;
import org.example.dto.TeamFormDto;
import org.example.exception.TeamAlreadyExistException;
import org.example.exception.TeamNotFoundException;
import org.example.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id){

        try {
            Team team = teamService.getById(id);

            return ResponseEntity.status(HttpStatus.FOUND).body(team);
        } catch (TeamNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }


    }

    @GetMapping
    public ResponseEntity all() {
        List<Team> teams = teamService.getAll();

        return ResponseEntity.status(HttpStatus.FOUND).body(teams);
    }

    @PostMapping
    public JsonResponse post(@RequestBody Team team) {

        try {
            Team createdTeam = teamService.createTeam(team);
            JsonResponse jsonResponse = new JsonResponse("Post controller called with Team name = " + createdTeam.getName() );
            System.out.println(jsonResponse.getMessage());
            return jsonResponse;
        } catch(TeamAlreadyExistException e) {
            JsonResponse jsonResponse = new JsonResponse(e.getMessage());
            return jsonResponse;
        }





    }
    @PutExchange("{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody TeamFormDto teamFormDto ) {
        try {
            Team createdTeam = teamService.updateTeam(id, teamFormDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdTeam);
        } catch(TeamNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        try {
            Team createdTeam = teamService.deleteTeam(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdTeam);
        } catch(TeamNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
