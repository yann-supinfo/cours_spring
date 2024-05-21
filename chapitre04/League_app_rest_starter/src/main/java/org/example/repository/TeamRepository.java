package org.example.repository;

import org.example.dto.Team;
import org.example.dto.TeamFormDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepository {

    private final JdbcTemplate jdbcTemplate;

    public TeamRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Optional<Team> getTeamById(int id) {
        List<Team> teams = jdbcTemplate.query("SELECT * FROM team WHERE id = ?", new Object[]  {id},
                (ResultSet rs, int rowNum ) -> {
                    Team teamTmp = new Team(rs.getLong("id"), rs.getString("name"));
                    return teamTmp;
                }
        );
        if (teams.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(teams.get(0));
    }

    public Boolean isTeamExist(Team team) {
      List<Team> teams = jdbcTemplate.query("SELECT * FROM team WHERE name = ?", new Object[]  {team.getName()},
              (ResultSet rs, int rowNum ) -> {
                    Team teamTmp = new Team(rs.getLong("id"), rs.getString("name"));
                    return teamTmp;
                }
        );
       return teams.isEmpty() ? false : true;
    }

    public List<Team> getAll() {
        List<Team> teams = jdbcTemplate.query("SELECT * FROM team",
                (ResultSet rs, int rowNum ) -> {
                    Team teamTmp = new Team(rs.getLong("id"), rs.getString("name"));
                    return teamTmp;
                }
        );
        return teams;
    }



    public Team add(Team team) {
        jdbcTemplate.update("INSERT INTO team (name) VALUES (?)", team.getName());
        return team;
    }

    public void update(int id, TeamFormDto teamFormDto) {
        jdbcTemplate.update("UPDATE team SET name = ? WHERE id = ?", new Object[] { teamFormDto.getName(), id });
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM team WHERE id = ?", new Object[] { id });
    }


}
