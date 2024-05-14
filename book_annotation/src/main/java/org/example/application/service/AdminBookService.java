package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.MysqlBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

@PropertySource("classpath:application.properties")
public class AdminBookService implements ServiceBookInterface {

    private MysqlBookRepository mysqlBookRepository;
    private BookFactoryManager bookFactoryManager;
    @Value("${service.name}")
    private String name;

    @Autowired
    public AdminBookService(MysqlBookRepository mysqlRepository,
                            BookFactoryManager factory

                            ) {
        mysqlBookRepository = mysqlRepository;
        bookFactoryManager = factory;
    }


    public List<Book> getBooks() {
        System.out.println("Utilisation du service " + this.name + ".");
        return mysqlBookRepository.getBooks();
    }
    public void addBook(String auteur, String name, int nbPage, String type) {
        System.out.println("Utilisation du service " + this.name + ".");
        Book book = this.bookFactoryManager.createBook(auteur, name, nbPage, type);

        mysqlBookRepository.addBook(book);

    }

    public void deleteBook(int index) {
        System.out.println("Utilisation du service " + this.name + ".");
        mysqlBookRepository.deleteBookByIndex(index);
    }

}
