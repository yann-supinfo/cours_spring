package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.BookRepositoryInterface;
import org.example.infrastructure.InMemoryBookRepository;
import org.example.infrastructure.MysqlBookRepository;

import java.util.List;

public class AdminBookService implements  ServiceBookInterface {

    private BookRepositoryInterface bookRepositoryInterface;
    private BookFactoryManager bookFactoryManager;

    public AdminBookService(BookRepositoryInterface bookInterface) {
        bookRepositoryInterface = bookInterface;
        bookFactoryManager = new BookFactoryManager();
    }

    public List<Book> getBooks() {
        return bookRepositoryInterface.getBooks();
    }

    public void addBook(String auteur, String name, int nbPage, String type) {

        Book book = this.bookFactoryManager.createBook(auteur, name, nbPage, type);

        bookRepositoryInterface.addBook(book);

    }

    public void borrowBook(int index) {

        System.out.println("Vous n'êtes pas membre");

    }

}
