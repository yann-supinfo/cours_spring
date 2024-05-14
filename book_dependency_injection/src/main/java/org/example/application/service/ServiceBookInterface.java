package org.example.application.service;

import org.example.domain.Book;

import java.util.List;

public interface ServiceBookInterface {
    public List<Book> getBooks();
    public void addBook(String auteur, String name, int nbPage, String type);
    public void borrowBook(int index);
}
