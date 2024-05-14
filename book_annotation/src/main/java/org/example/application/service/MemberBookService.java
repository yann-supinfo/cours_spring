package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.BookRepositoryInterface;
import org.example.infrastructure.InMemoryBookRepository;
import org.example.infrastructure.MysqlBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberBookService implements ServiceBookInterface {

    private MysqlBookRepository mysqlBookRepository;

    private String name;
    private BookFactoryManager bookFactoryManager;

    @Autowired
     public MemberBookService(MysqlBookRepository mysqlRepository, BookFactoryManager factory) {
         mysqlBookRepository = mysqlRepository;
         bookFactoryManager = factory;
    }

    public List<Book> getBooks() {
        System.out.println("Utilisation du service Member.");
        return mysqlBookRepository.getBooks();
    }
    public void addBook(String auteur, String name, int nbPage, String type) {
        System.out.println("Utilisation du service Member.");
        Book book = this.bookFactoryManager.createBook(auteur, name, nbPage, type);

        mysqlBookRepository.addBook(book);

    }

    public void deleteBook(int index) {

        System.out.println("Utilisation du service Member.");
        mysqlBookRepository.deleteBookByIndex(index);

    }
}
