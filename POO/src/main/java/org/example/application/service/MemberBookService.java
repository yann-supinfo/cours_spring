package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.infrastructure.InMemoryBookRepository;

import java.util.List;

public class MemberBookService {
    private InMemoryBookRepository inMemoryBookRepository;
    private BookFactoryManager bookFactoryManager;

    public MemberBookService() {
        inMemoryBookRepository = new InMemoryBookRepository();
        bookFactoryManager = new BookFactoryManager();
    }

    public List<Book> getBooksMember() {
        return inMemoryBookRepository.getBooksInMemory();
    }
    public void addBookMember(String auteur, String name, int nbPage, String type) {
        System.out.println("Vous n'êtes pas admin");

    }

    public void borrowBookMember(int index) {

        inMemoryBookRepository.deleteBookByIndexInMemory(index);
        System.out.println("Book borrow");
    }
}
