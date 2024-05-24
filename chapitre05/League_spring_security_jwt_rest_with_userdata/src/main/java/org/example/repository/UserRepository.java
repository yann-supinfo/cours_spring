package org.example.repository;

import org.example.dto.Role;
import org.example.dto.User;
import org.example.mapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserRowMapper());
    }

    public void save(User user) {

        String sql = "INSERT INTO user (username, password) VALUE (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());

        String getIdSql = "SELECT id FROM user WHERE username = ?";
        Long userId = jdbcTemplate.queryForObject(getIdSql, new Object[]{user.getUsername()}, Long.class);
        user.setId(userId);

        for (Role role : user.getRoles()) {
            jdbcTemplate.update("INSERT INTO user_role (user_id, role_id) VALUES (?, ?)", userId, role.getId());
        }

    }


}

