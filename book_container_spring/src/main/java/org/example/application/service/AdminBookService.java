package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.BookRepositoryInterface;
import org.example.infrastructure.InMemoryBookRepository;

import java.util.List;

public class AdminBookService implements ServiceBookInterface {

    private BookRepositoryInterface bookRepositoryInterface;
    private BookFactoryManager bookFactoryManager;

    public BookRepositoryInterface getBookRepositoryInterface() {
        return bookRepositoryInterface;
    }

    public void setBookRepositoryInterface(BookRepositoryInterface bookRepositoryInterface) {
        this.bookRepositoryInterface = bookRepositoryInterface;
    }

    public BookFactoryManager getBookFactoryManager() {
        return bookFactoryManager;
    }

    public void setBookFactoryManager(BookFactoryManager bookFactoryManager) {
        this.bookFactoryManager = bookFactoryManager;
    }

    /*public AdminBookService(BookRepositoryInterface bookInterface) {
        bookRepositoryInterface = bookInterface;
        bookFactoryManager = new BookFactoryManager();
    }*/

    public List<Book> getBooks() {
        System.out.println("Utilisation du service Admin.");
        return bookRepositoryInterface.getBooks();
    }
    public void addBook(String auteur, String name, int nbPage, String type) {
        System.out.println("Utilisation du service Admin");
        Book book = this.bookFactoryManager.createBook(auteur, name, nbPage, type);

        bookRepositoryInterface.addBook(book);

    }

    public void deleteBook(int index) {
        System.out.println("Utilisation du service Admin.");
        bookRepositoryInterface.deleteBookByIndex(index);
    }

}
