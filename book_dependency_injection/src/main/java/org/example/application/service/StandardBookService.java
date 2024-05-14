package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.BookRepositoryInterface;
import org.example.infrastructure.InMemoryBookRepository;

import java.util.List;

public class StandardBookService  implements  ServiceBookInterface {

    private BookRepositoryInterface bookRepositoryInterface;
    private BookFactoryManager bookFactoryManager;

    public StandardBookService(BookRepositoryInterface bookInterface) {
        bookRepositoryInterface = new InMemoryBookRepository();
        bookFactoryManager = new BookFactoryManager();
    }

    public List<Book> getBooks() {
        return bookRepositoryInterface.getBooks();
    }

    public void addBook(String auteur, String name, int nbPage, String type) {
        System.out.println("Vous n'êtes pas admin");
    }

    public void borrowBook(int index) {
        System.out.println("Vous n'êtes pas membre");

    }


}
