package org.example.application.controller;

import org.example.application.service.ServiceBookInterface;
import org.example.application.service.StandardBookService;
import org.example.domain.Book;
import org.example.domain.BookFactoryManager;

import java.util.ArrayList;
import java.util.List;

public class BookController {


 private ServiceBookInterface serviceBookInterface;

    public ServiceBookInterface getServiceBookInterface() {
        return serviceBookInterface;
    }

    public void setServiceBookInterface(ServiceBookInterface serviceBookInterface) {
        this.serviceBookInterface = serviceBookInterface;
    }

 /*
  public BookController(ServiceBookInterface serviceInterface) {
      this.serviceBookInterface = serviceInterface;
  }*/

  public List<Book> getBooks() {
        return serviceBookInterface.getBooks();
  }

  public void addBook(String auteur, String name, int nbPage, String type) {
        this.serviceBookInterface.addBook(auteur, name, nbPage, type);
  }

  public void borrowBook(int index) {
      this.serviceBookInterface.deleteBook(index);
  }

}
