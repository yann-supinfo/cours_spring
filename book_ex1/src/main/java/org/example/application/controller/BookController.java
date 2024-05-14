package org.example.application.controller;

import org.example.application.service.StandardBookService;
import org.example.domain.Book;
import org.example.domain.BookFactoryManager;

import java.util.ArrayList;
import java.util.List;

public class BookController {


 private StandardBookService standardBookService;

  public BookController() {
      this.standardBookService = new StandardBookService();
  }

  public List<Book> getBooks() {
        return standardBookService.getBooksStandard();
  }

  public void addBook(String auteur, String name, int nbPage, String type) {
        this.standardBookService.addBookStandard(auteur, name, nbPage, type);
  }

  public void borrowBook(int index) {
      this.standardBookService.borrowBook(index);
  }

}
