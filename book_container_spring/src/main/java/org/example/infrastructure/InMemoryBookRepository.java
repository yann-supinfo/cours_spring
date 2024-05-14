package org.example.infrastructure;

import org.example.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBookRepository implements BookRepositoryInterface {

    public List<Book> books;
    public BookDataSourceSingleton bookDataSourceSingleton;

    public InMemoryBookRepository() {
        this.bookDataSourceSingleton = BookDataSourceSingleton.getInstance();

    }

    public List<Book> getBooks() {
        return this.bookDataSourceSingleton.books;
    }

    public void addBook(Book book) {
        this.bookDataSourceSingleton.books.add(book);
        System.out.println("Le livre " + book.getName() + " a été ajouté au repository en RAM avec succès");
    }

    public void deleteBookByCode(String code) {
        for (int i = 0; i < bookDataSourceSingleton.books.size(); i++) {

            String num = bookDataSourceSingleton.books.get(i).getCode();

            if (code.equals(num)) {
                String name = bookDataSourceSingleton.books.get(i).getName();
                bookDataSourceSingleton.books.remove(i);

                System.out.println("Le livre " + name + " et avec le code " + num + " a été supprimer de la RAM avec succès");

            }
        }

    }

    public void deleteBookByIndex(int index) {
        for (int i = 0; i < bookDataSourceSingleton.books.size(); i++) {


            if (i == index) {
                String name = bookDataSourceSingleton.books.get(i).getName();
                String num = bookDataSourceSingleton.books.get(i).getCode();
                bookDataSourceSingleton.books.remove(i);

                System.out.println("Le livre " + name + " et avec le code " + num + " a été supprimer de la RAM avec succès");

            }
        }

    }
}