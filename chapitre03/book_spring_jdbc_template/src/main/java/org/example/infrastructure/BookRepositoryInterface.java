package org.example.infrastructure;

import org.example.domain.Book;

import java.util.List;

public interface BookRepositoryInterface {
    public List<Book> getBooks();
    public void addBook(Book book);
    public void deleteBookByIndex(int index);
}
