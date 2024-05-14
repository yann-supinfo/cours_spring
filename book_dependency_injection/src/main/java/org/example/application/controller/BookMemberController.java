/*
package org.example.application.controller;

import org.example.application.service.MemberBookService;
import org.example.application.service.StandardBookService;
import org.example.domain.Book;

import java.util.List;

public class BookMemberController {

    private MemberBookService memberBookService;

    public BookMemberController() {
        this.memberBookService = new MemberBookService();
    }

    public List<Book> getBooks() {
        return memberBookService.getBooksMember();
    }

    public void addBook(String auteur, String name, int nbPage, String type) {
        this.memberBookService.addBookMember(auteur, name, nbPage, type);
    }

    public void borrowBook(int index) {
        this.memberBookService.borrowBookMember(index);
    }

}
*/