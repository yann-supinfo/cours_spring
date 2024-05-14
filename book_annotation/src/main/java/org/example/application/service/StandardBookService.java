package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.BookRepositoryInterface;
import org.example.infrastructure.InMemoryBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StandardBookService implements ServiceBookInterface {


    private InMemoryBookRepository  inMemoryBookRepository;
    private BookFactoryManager bookFactoryManager;

    @Autowired
    public StandardBookService(InMemoryBookRepository InMemoryRepository, BookFactoryManager factory) {
            inMemoryBookRepository = InMemoryRepository;
            bookFactoryManager = factory;
    }

    public List<Book> getBooks() {
        System.out.println("Utilisation du service Standard.");
        return inMemoryBookRepository.getBooks();
    }

    public void addBook(String auteur, String name, int nbPage, String type) {
        System.out.println("Utilisation du service Standard.");
        Book book = this.bookFactoryManager.createBook(auteur, name, nbPage, type);

        inMemoryBookRepository.addBook(book);

    }

    public void deleteBook(int index) {
        System.out.println("Utilisation du service Standard.");
        inMemoryBookRepository.deleteBookByIndex(index);
    }


}
