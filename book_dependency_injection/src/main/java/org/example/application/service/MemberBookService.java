package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.BookRepositoryInterface;
import org.example.infrastructure.InMemoryBookRepository;
import org.example.infrastructure.MysqlBookRepository;

import java.util.List;

public class MemberBookService  implements  ServiceBookInterface {
    private BookRepositoryInterface bookRepositoryInterface;
    private BookFactoryManager bookFactoryManager;

    public MemberBookService(BookRepositoryInterface bookInterface) {
        bookRepositoryInterface = new MysqlBookRepository();
        bookFactoryManager = new BookFactoryManager();
    }

    public List<Book> getBooks() {
        return bookRepositoryInterface.getBooks();
    }

    public void addBook(String auteur, String name, int nbPage, String type) {
        System.out.println("Vous n'êtes pas admin");

    }

    public void borrowBook(int index) {

        bookRepositoryInterface.deleteBookByIndex(index);
        System.out.println("Book borrow");
    }
}
