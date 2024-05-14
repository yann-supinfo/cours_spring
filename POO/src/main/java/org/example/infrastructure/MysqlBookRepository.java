package org.example.infrastructure;

import org.example.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class MysqlBookRepository {

    public BookDataSourceSingleton bookDataSourceSingleton;

    public MysqlBookRepository() {

        this.bookDataSourceSingleton = BookDataSourceSingleton.getInstance();
        System.out.println("Mysql Repository Created");
    }

    public List<Book> getBooksMysql() {
        return this.bookDataSourceSingleton.books;
    }

    public void addBookMysql(Book book) {
        this.bookDataSourceSingleton.books.add(book);
        System.out.println("Le livre " + book.getName() + " a été ajouté au repository mysql avec succès");
    }

    public void deleteBookByCodeMysql(String code) {
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

    public void deleteBookByIndexMysql(int index) {
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
