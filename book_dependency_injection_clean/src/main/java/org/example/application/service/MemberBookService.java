package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.BookRepositoryInterface;
import org.example.infrastructure.InMemoryBookRepository;

import java.util.List;

public class MemberBookService implements ServiceBookInterface {

    private BookRepositoryInterface bookRepositoryInterface;
    private BookFactoryManager bookFactoryManager;

    public MemberBookService(BookRepositoryInterface bookInterface) {
        bookRepositoryInterface = bookInterface;
        bookFactoryManager = new BookFactoryManager();
    }

    public List<Book> getBooks() {
        System.out.println("Utilisation du service Member.");
        return bookRepositoryInterface.getBooks();
    }
    public void addBook(String auteur, String name, int nbPage, String type) {
        System.out.println("Utilisation du service Member.");
        Book book = this.bookFactoryManager.createBook(auteur, name, nbPage, type);

        bookRepositoryInterface.addBook(book);

    }

    public void deleteBook(int index) {

        System.out.println("Utilisation du service Member.");
        bookRepositoryInterface.deleteBookByIndex(index);

    }
}
