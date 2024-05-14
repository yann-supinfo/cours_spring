package org.example.infrastructure;

import org.example.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDataSourceSingleton {
    private static BookDataSourceSingleton instance;
    public List<Book> books;

    private BookDataSourceSingleton () {
        this.books = new ArrayList<>();
    }

    public static BookDataSourceSingleton getInstance() {
        if (instance == null) {
            System.out.println("Create instance");
            instance = new BookDataSourceSingleton();
        }
        System.out.println("get instance");
        return instance;
    }
}
