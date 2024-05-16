package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.MysqlBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements ServiceBookInterface {

    private MysqlBookRepository mysqlBookRepository;

    private String name;
    private BookFactoryManager bookFactoryManager;


     public BookService(MysqlBookRepository mysqlRepository, BookFactoryManager factory) {
         mysqlBookRepository = mysqlRepository;
         bookFactoryManager = factory;
    }

    public List<Book> getBooks() {
        System.out.println("Utilisation du service Book.");
        return mysqlBookRepository.getBooks();
    }
    public void addBook(String auteur, String name, int nbPage, String type) {
        System.out.println("Utilisation du service Book.");
        Book book = this.bookFactoryManager.createBook(0, auteur, name, nbPage, type);

        mysqlBookRepository.addBook(book);

    }

    public void deleteBook(int index) {

        System.out.println("Utilisation du service Book.");
        mysqlBookRepository.deleteBookByIndex(index);

    }
}
