package org.example.infrastructure;

import org.example.domain.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MysqlBookRepository implements BookRepositoryInterface {

    public BookDataSourceSingleton bookDataSourceSingleton;

    public MysqlBookRepository() {

        this.bookDataSourceSingleton = BookDataSourceSingleton.getInstance();
        System.out.println("Mysql Repository Created");
    }

    public List<Book> getBooks() {
        return this.bookDataSourceSingleton.books;
    }

    public void addBook(Book book) {
        this.bookDataSourceSingleton.books.add(book);
        System.out.println("Le livre " + book.getName() + " a été ajouté au repository mysql avec succès");
    }

    public void deleteBookByCode(String code) {
        for (int i = 0; i < this.bookDataSourceSingleton.books.size(); i++) {

            String num = bookDataSourceSingleton.books.get(i).getCode();
            System.out.println("Index" + i);
            System.out.println(code == num);
            if (code.equals(num)) {
                System.out.println("trigger");
                String name = bookDataSourceSingleton.books.get(i).getName();
                bookDataSourceSingleton.books.remove(i);

                System.out.println("Le livre " + name + " et avec le code " + num + " a été supprimer de la base de donnée mysql avec succès");

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
