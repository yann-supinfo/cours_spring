package org.example.application.controller;

import org.example.application.service.AdminBookService;
import org.example.application.service.MemberBookService;
import org.example.application.service.ServiceBookInterface;
import org.example.application.service.StandardBookService;
import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookController {


    private AdminBookService adminBookService;

    @Autowired
    public BookController(AdminBookService adminBookService) {
        this.adminBookService = adminBookService;
    }



  public List<Book> getBooks() {
        return adminBookService.getBooks();
  }

  public void addBook(String auteur, String name, int nbPage, String type) {
        this.adminBookService.addBook(auteur, name, nbPage, type);
  }

  public void borrowBook(int index) {
      this.adminBookService.deleteBook(index);
  }

}
