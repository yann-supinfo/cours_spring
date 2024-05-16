package org.example.infrastructure;

import org.example.domain.Client;
import org.example.mapper.BookRowMapper;
import org.example.mapper.ClientRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MysqlClientRepository implements ClientRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;
    private final ClientRowMapper clientRowMapper;

    public MysqlClientRepository(JdbcTemplate jdbcTemplate, ClientRowMapper clientRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.clientRowMapper = clientRowMapper;
    }

    @Override
    public List<Client> getClients() {
        return jdbcTemplate.query("SELECT id, firstname, lastname FROM client",
                (rs, rowNum) -> clientRowMapper.mapRow(rs, rowNum) );
    }

    @Override
    public void addClient(Client client) {
        jdbcTemplate.update("INSERT INTO client (firstname, lastname) VALUE (?, ?)",
                client.getFirstname(), client.getLastname()
        );
    }

    @Override
    public void deleteClientByIndex(int index) {
        jdbcTemplate.update("DELETE FROM client where id = ?", index);
    }
}
