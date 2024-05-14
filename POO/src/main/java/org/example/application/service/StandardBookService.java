package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.InMemoryBookRepository;

import java.util.List;

public class StandardBookService {

    private InMemoryBookRepository inMemoryBookRepository;
    private BookFactoryManager bookFactoryManager;

    public StandardBookService() {
        inMemoryBookRepository = new InMemoryBookRepository();
        bookFactoryManager = new BookFactoryManager();
    }

    public List<Book> getBooksStandard() {
        return inMemoryBookRepository.getBooksInMemory();
    }

    public void addBookStandard(String auteur, String name, int nbPage, String type) {
        System.out.println("Vous n'êtes pas admin");



    }

    public void borrowBook(int index) {
        System.out.println("Vous n'êtes pas membre");

    }


}
