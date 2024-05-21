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

    public Boolean isTeamExistInSaison(long saisonId, long teamId) {
        List<Team> teams = jdbcTemplate.query("SELECT * FROM saison_team WHERE saisonId = ? AND teamId = ?", new Object[]  {saisonId, teamId},
                (ResultSet rs, int rowNum ) -> {
                    Team teamTmp = new Team();
                    return teamTmp;
                }
        );
        return teams.isEmpty() ? false : true;
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


    public List<Team> findAllBySaisonId(Long saisonId) {
        return jdbcTemplate.query("SELECT t.* FROM team t INNER JOIN saison_teams st ON t.id = st.team_id WHERE st.saison_id = ?", new Object[]{saisonId}, (ResultSet rs, int rowNum) -> {
            Team team = new Team();
            team.setId(rs.getLong("id"));
            team.setName(rs.getString("name"));
            return team;
        });
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
