package org.example.mapper;

import org.example.domain.Book;
import org.example.domain.Client;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClientRowMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"));
        return client;
    }
}
