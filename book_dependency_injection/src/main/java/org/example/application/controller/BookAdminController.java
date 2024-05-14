/*package org.example.application.controller;

import org.example.application.service.AdminBookService;
import org.example.application.service.MemberBookService;
import org.example.application.service.ServiceBookInterface;
import org.example.domain.Book;

import java.util.List;

public class BookAdminController {
    private ServiceBookInterface serviceBookInterface;

    public BookAdminController(ServiceBookInterface serviceInterface) {
        this.serviceBookInterface = serviceInterface;
    }

    public List<Book> getBooks() {
        return serviceBookInterface.getBooks();
    }

    public void addBook(String auteur, String name, int nbPage, String type) {
        this.serviceBookInterface.addBook(auteur, name, nbPage, type);
    }

    public void borrowBook(int index) {
        this.serviceBookInterface.borrowBook(index);
    }
}
*/