package org.example.repository;

import org.example.dto.Saison;
import org.example.dto.Team;
import org.example.dto.TeamSaison;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class SaisonRepository {
    private final JdbcTemplate jdbcTemplate;

    public SaisonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Saison> findAll() {
        return jdbcTemplate.query("SELECT * FROM saison", (ResultSet rs, int rowNum) -> {
            Saison saison = new Saison();
            saison.setId(rs.getLong("id"));
            saison.setLibelle(rs.getString("libelle"));
            saison.setYear(rs.getInt("year"));
            return saison;
        });
    }

    public Optional<Saison> findById(Long id) {
        List<Saison> saisons = jdbcTemplate.query("SELECT * FROM saison WHERE id = ?", new Object[]{id}, (ResultSet rs, int rowNum) -> {
            Saison saison = new Saison();
            saison.setId(rs.getLong("id"));
            saison.setLibelle(rs.getString("libelle"));
            saison.setYear(rs.getInt("year"));
            return saison;
        });
        if (saisons.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(saisons.get(0));
    }

    public Optional<Saison> findByYearAndNotById(int year, int id) {
        List<Saison> saisons = jdbcTemplate.query("SELECT * FROM saison WHERE year = ? AND id != ?", new Object[]{year, id}, (ResultSet rs, int rowNum) -> {
            Saison saison = new Saison();
            saison.setId(rs.getLong("id"));
            saison.setLibelle(rs.getString("libelle"));
            saison.setYear(rs.getInt("year"));
            return saison;
        });
        if (saisons.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(saisons.get(0));
    }

    public Optional<Saison> findByYear(int year) {
        List<Saison> saisons = jdbcTemplate.query("SELECT * FROM saison WHERE year = ?", new Object[]{year}, (ResultSet rs, int rowNum) -> {
            Saison saison = new Saison();
            saison.setId(rs.getLong("id"));
            saison.setLibelle(rs.getString("libelle"));
            saison.setYear(rs.getInt("year"));
            return saison;
        });
        if (saisons.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(saisons.get(0));
    }

    public List<Team> findAllBySaisonId(Long saisonId) {
        return jdbcTemplate.query("SELECT t.* FROM team t INNER JOIN saison_teams st ON t.id = st.team_id WHERE st.saison_id = ?", new Object[]{saisonId}, (ResultSet rs, int rowNum) -> {
            Team team = new Team();
            team.setId(rs.getLong("id"));
            team.setName(rs.getString("name"));
            return team;
        });
    }


    public void save(Saison saison) {
        jdbcTemplate.update("INSERT INTO saison (libelle, year) VALUES (?, ?)", saison.getLibelle(), saison.getYear());
    }

    public void update(int id, Saison saison) {
        String sql = "UPDATE saison SET year = ?, libelle = ?  WHERE id = ?";

        jdbcTemplate.update(sql, saison.getYear(), saison.getLibelle(), id);


    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM saison WHERE id = ?", id);
    }

    public void addTeamToSaison(int teamId, int saisonId) {
        jdbcTemplate.update("INSERT INTO saison_team (teamId, saisonId) VALUES (?, ?)", teamId, saisonId);
    }

    public void removeTeamFromSaison(Long teamId, Long saisonId) {
        jdbcTemplate.update("DELETE FROM saison_team WHERE teamId = ? AND saisonId = ?", teamId, saisonId);
    }

    public Boolean findByTeamIdAndSaisonId(int teamId, int saisonId) {
        List<TeamSaison> saisons = jdbcTemplate.query("SELECT * FROM saison_team WHERE teamId = ? AND saisonId = ?", new Object[]{teamId, saisonId}, (ResultSet rs, int rowNum) -> {
            TeamSaison teamSaison = new TeamSaison();
            teamSaison.setId(rs.getLong("id"));
            teamSaison.setTeamId(rs.getLong("teamId"));
            teamSaison.setSaisonId(rs.getLong("saisonId"));
            return teamSaison;
        });
        if (saisons.isEmpty()) {
            return false;
        }
        return true;
    }
}
