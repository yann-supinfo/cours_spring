package org.example.infrastructure;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;

import org.example.mapper.BookRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MysqlBookRepository implements BookRepositoryInterface {


    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper bookRowMapper;

    public MysqlBookRepository(JdbcTemplate jdbcTemplate, BookRowMapper bookRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
    }

    @Override
    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT id, title, author FROM book",
             (rs, rowNum) -> bookRowMapper.mapRow(rs, rowNum) );
    }

    @Override
    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO book (title, author) VALUE (?, ?)",
                book.getName(), book.getAuthor()
                );
    }

    @Override
    public void deleteBookByIndex(int index) {
        jdbcTemplate.update("DELETE FROM book where id = ?", index);
    }
}
