package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.InMemoryBookRepository;

import java.util.List;

public class AdminBookService {

    private InMemoryBookRepository inMemoryBookRepository;
    private BookFactoryManager bookFactoryManager;

    public AdminBookService() {
        inMemoryBookRepository = new InMemoryBookRepository();
        bookFactoryManager = new BookFactoryManager();
    }

    public List<Book> getBooksStandard() {
        return inMemoryBookRepository.getBooksInMemory();
    }
    public void addBookAdmin(String auteur, String name, int nbPage, String type) {

        Book book = this.bookFactoryManager.createBook(auteur, name, nbPage, type);

        inMemoryBookRepository.addBookInMemory(book);

    }

    public void borrowBookAdmin(int index) {

        System.out.println("Vous n'êtes pas membre");

    }

}
