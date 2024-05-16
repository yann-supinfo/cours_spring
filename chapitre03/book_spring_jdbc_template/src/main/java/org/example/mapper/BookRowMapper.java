package org.example.mapper;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<Book> {


    private final BookFactoryManager bookFactoryManager;

    public BookRowMapper(BookFactoryManager bookFactoryManager) {
        this.bookFactoryManager = bookFactoryManager;
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = this.bookFactoryManager.createBook( rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                100,
                "ADV"
        );
        return book;
    }
}
