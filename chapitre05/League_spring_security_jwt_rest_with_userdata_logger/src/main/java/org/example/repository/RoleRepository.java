package org.example.repository;

import org.example.dto.Role;
import org.example.mapper.RoleRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Role findByName(String name) {
        String sql = "SELECT * FROM role WHERE name = ?";
        List<Role> roles = jdbcTemplate.query(sql, new Object[]{name}, new RoleRowMapper());

        return roles.isEmpty() ? null : roles.get(0);
    }

    public List<Role> getRolesByUsername(String username) {
        String sql = "SELECT r.id, r.name FROM role r " +
                "JOIN user_role ur ON r.id = ur.role_id  " +
                "JOIN user u ON ur.user_id = u.id " +
                "WHERE u.username = ?";
        List<Role> roles = jdbcTemplate.query(sql, new Object[]{username}, new RoleRowMapper());

        return roles;
    }


}
