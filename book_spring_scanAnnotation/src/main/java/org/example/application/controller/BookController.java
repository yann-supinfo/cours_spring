package org.example.application.controller;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.example.application.service.BookService;
import org.example.domain.Book;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {


    private BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



  public List<Book> getBooks() {
        return bookService.getBooks();
  }

  public void addBook(String auteur, String name, int nbPage, String type) {
        this.bookService.addBook(auteur, name, nbPage, type);
  }

  public void borrowBook(int index) {
      this.bookService.deleteBook(index);
  }

    @PostConstruct
    public void SayHello() {
        System.out.println("Say Hello Controller called");
    }

    @PreDestroy
    public void SayGoodBye() {
        System.out.println("Say GoodBye Controller called");
    }
}
