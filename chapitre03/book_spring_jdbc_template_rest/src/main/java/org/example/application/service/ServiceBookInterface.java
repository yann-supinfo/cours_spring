package org.example.application.service;

import org.example.domain.Book;

import java.util.List;

public interface ServiceBookInterface {
    List<Book> getBooks();
    public void addBook(String title, String author);
    public void deleteBook(int index);


}
