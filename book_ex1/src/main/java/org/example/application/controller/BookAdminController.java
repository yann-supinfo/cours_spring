package org.example.application.controller;

import org.example.application.service.AdminBookService;
import org.example.application.service.MemberBookService;
import org.example.domain.Book;

import java.util.List;

public class BookAdminController {
    private AdminBookService adminBookService;

    public BookAdminController() {
        this.adminBookService = new AdminBookService();
    }

    public List<Book> getBooks() {
        return adminBookService.getBooksStandard();
    }

    public void addBook(String auteur, String name, int nbPage, String type) {
        this.adminBookService.addBookAdmin(auteur, name, nbPage, type);
    }

    public void borrowBook(int index) {
        this.adminBookService.borrowBookAdmin(index);
    }
}
