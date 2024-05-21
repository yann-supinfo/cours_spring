package org.example.services;

import org.example.dto.Team;
import org.example.dto.TeamFormDto;
import org.example.exception.TeamAlreadyExistException;
import org.example.exception.TeamNotFoundException;
import org.example.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public Team getById(int id) throws TeamNotFoundException {

        Optional<Team> team = teamRepository.getTeamById(id);

        if (team.isEmpty()) {
            throw new TeamNotFoundException("Team not found");
        }

        return team.get();

    }

    public List<Team> getAll() {
        return teamRepository.getAll();
    }


    public Team createTeam(Team team) throws TeamAlreadyExistException {

        if (teamRepository.isTeamExist(team)) {
            throw new TeamAlreadyExistException("Team Already Exist");
        }

        return teamRepository.add(team);
    }

    public Team updateTeam(int id, TeamFormDto teamFormDto) throws TeamNotFoundException {

        Optional<Team> team = teamRepository.getTeamById(id);

        if (team.isEmpty()) {
            throw new TeamNotFoundException("Team not found");
        }
        teamRepository.update(id, teamFormDto);
        team.get().setName(teamFormDto.getName());
        return team.get();
    }

    public Team deleteTeam(int id) throws TeamNotFoundException {

        Optional<Team> team = teamRepository.getTeamById(id);

        if (team.isEmpty()) {
            throw new TeamNotFoundException("Team not found");
        }
        teamRepository.delete(id);
        team.get();
        return team.get();
    }

}
