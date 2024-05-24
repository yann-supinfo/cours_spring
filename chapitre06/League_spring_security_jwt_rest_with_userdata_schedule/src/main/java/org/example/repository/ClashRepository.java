package org.example.repository;

import org.example.dto.Clash;
import org.example.dto.ClashFormDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class ClashRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClashRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Clash> findByDayAndTeam(Long dayId, Long homeTeamId, Long externalTeamId) {
        List<Clash> matchsList = jdbcTemplate.query("SELECT * FROM clash WHERE dayId = ? AND homeTeamId = ? AND externTeamId = ?", new Object[]{dayId, homeTeamId, externalTeamId}, (ResultSet rs, int rowNum) -> {
            Clash clash = new Clash();
            clash.setId(rs.getLong("id"));
            clash.setDayId(rs.getLong("dayId"));
            clash.setHomeTeamId(rs.getLong("homeTeamId"));
            clash.setAwayTeamId(rs.getLong("externalTeamId"));
            clash.setMatchDate(rs.getTimestamp("clashDate").toLocalDateTime());
            return clash;
        });
        if (matchsList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(matchsList.get(0));
    }

    public List<Clash> findAllByDayId(Long dayId) {
        return jdbcTemplate.query("SELECT * FROM clash WHERE dayId = ?", new Object[]{dayId}, (ResultSet rs, int rowNum) -> {
            Clash clash = new Clash();
            clash.setId(rs.getLong("id"));
            clash.setDayId(rs.getLong("dayId"));
            clash.setHomeTeamId(rs.getLong("homeTeamId"));
            clash.setAwayTeamId(rs.getLong("externalTeamId"));
            clash.setMatchDate(rs.getTimestamp("clashDate").toLocalDateTime());
            return clash;
        });
    }

    public Optional<Clash> findById(Long id) {
        List<Clash> matchsList = jdbcTemplate.query("SELECT * FROM clash WHERE id = ?", new Object[]{id}, (ResultSet rs, int rowNum) -> {
            Clash clash = new Clash();
            clash.setId(rs.getLong("id"));
            clash.setDayId(rs.getLong("dayId"));
            clash.setHomeTeamId(rs.getLong("homeTeamId"));
            clash.setAwayTeamId(rs.getLong("externalTeamId"));
            clash.setMatchDate(rs.getTimestamp("clashDate").toLocalDateTime());
            return clash;
        });
        if (matchsList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(matchsList.get(0));
    }

    public void save(ClashFormDto clashFormDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse(clashFormDto.getClashDate(), formatter);
        jdbcTemplate.update("INSERT INTO clash (dayId, homeTeamId, externTeamId, clashDate) VALUES (?, ?, ?, ?)",
                clashFormDto.getDayId(), clashFormDto.getHomeTeamId(), clashFormDto.getAwayTeamId(), Timestamp.valueOf(dateTime));
    }

    public void updateClash(Long clashId, ClashFormDto clashFormDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse(clashFormDto.getClashDate(), formatter);
        String sql = "UPDATE clashes SET dayId = ?, homeTeamId = ?, externTeamId = ?, clashDate = ? WHERE id = ?";

        jdbcTemplate.update(sql, clashFormDto.getDayId(), clashFormDto.getHomeTeamId(), clashFormDto.getAwayTeamId(), Timestamp.valueOf(dateTime), clashId);
    }
}