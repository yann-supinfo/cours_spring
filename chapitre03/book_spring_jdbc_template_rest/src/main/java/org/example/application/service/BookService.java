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
    public void addBook(String title, String author) {
        System.out.println("Utilisation du service Book.");


        mysqlBookRepository.addBook(title, author);

    }

    public void deleteBook(int index) {

        System.out.println("Utilisation du service Book.");
        mysqlBookRepository.deleteBookByIndex(index);

    }
}
