package org.example.repository;

import org.example.dto.Day;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class DayRepository {

    private final JdbcTemplate jdbcTemplate;

    public DayRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Day> findAllBySaisonId(Long saisonId) {
        return jdbcTemplate.query("SELECT * FROM day WHERE saisonId = ?", new Object[]{saisonId}, (ResultSet rs, int rowNum) -> {
            Day day = new Day();
            day.setId(rs.getLong("id"));
            day.setNumero(rs.getInt("day"));
            day.setSaisonId(rs.getLong("saisonId"));
            return day;
        });
    }

    public Optional<Day> findByNumeroAndSaisonId(int numero, Long saisonId) {
        List<Day> days = jdbcTemplate.query("SELECT * FROM day WHERE day = ? AND saisonId = ?", new Object[]{numero, saisonId}, (ResultSet rs, int rowNum) -> {
            Day day = new Day();
            day.setId(rs.getLong("id"));
            day.setNumero(rs.getInt("day"));
            day.setSaisonId(rs.getLong("saisonId"));
            return day;
        });
        if (days.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(days.get(0));
    }

    public Optional<Day> findById(Long id) {
        List<Day> days = jdbcTemplate.query("SELECT * FROM day WHERE id = ?", new Object[]{id}, (ResultSet rs, int rowNum) -> {
            Day day = new Day();
            day.setId(rs.getLong("id"));
            day.setNumero(rs.getInt("day"));
            day.setSaisonId(rs.getLong("saisonId"));
            return day;
        });
        if (days.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(days.get(0));
    }

    public void save(Day day) {
        jdbcTemplate.update("INSERT INTO day (day, saisonId) VALUES (?, ?)", day.getNumero(), day.getSaisonId());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM day WHERE id = ?", id);
    }
}