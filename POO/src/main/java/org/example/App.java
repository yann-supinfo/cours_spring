package org.example;

import org.example.domain.*;
import org.example.infrastructure.InMemoryBookRepository;
import org.example.infrastructure.MysqlBookRepository;
import org.example.ui.UiManager;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UiManager uiManager = new UiManager();

        uiManager.run();

/*      ConnectionBDD conn1 = ConnectionBDD.getInstance("mysql connexion");
      ConnectionBDD conn2 = ConnectionBDD.getInstance("mysql connexion 2");

      System.out.println(conn1.name);
      System.out.println(conn2.name);
        UiManager uiManager = new UiManager();

        uiManager.run();

       BookFactoryManager bookFactoryManager = new BookFactoryManager();
        MysqlBookRepository mysqlBookRepository = new MysqlBookRepository();
        InMemoryBookRepository inMemoryBookRepository = new InMemoryBookRepository();

        Book livre1 =  bookFactoryManager.createBook("autheur1", "livre1", 200, "ADVd");
        Book livre2 =  bookFactoryManager.createBook("autheur2", "livre2", 200, "ADVd");
        Book livre3 =  bookFactoryManager.createBook("autheur3", "livre3", 200, "ADVd");

        mysqlBookRepository.addBookMysql(livre1);
        mysqlBookRepository.addBookMysql(livre2);

        List<Book> books = mysqlBookRepository.getBooksMysql();
        System.out.println("---Repo Mysql---");
        for (int i = 0; i < books.size(); i++) {
            books.get(i).displayInformation();
        }


        inMemoryBookRepository.addBookInMemory(livre1);
        inMemoryBookRepository.addBookInMemory(livre3);
        List<Book> books2 = inMemoryBookRepository.getBooksInMemory();
        System.out.println("---Repo RAM---");
        for (int i = 0; i < books2.size(); i++) {
            books2.get(i).displayInformation();
        }

        mysqlBookRepository.deleteBookByCodeMysql("LIT-2");

        books = mysqlBookRepository.getBooksMysql();
        System.out.println("---Repo Mysql---");
        for (int i = 0; i < books.size(); i++) {
            books.get(i).displayInformation();
        }

        books = inMemoryBookRepository.getBooksInMemory();
        System.out.println("---Repo RAM---");
        for (int i = 0; i < books.size(); i++) {
            books.get(i).displayInformation();
        }

      /*  AdventureBookFactory adventureBookFactory = new AdventureBookFactory();
        SfBookFactory sfBookFactory = new SfBookFactory();
        HistoryBookFactory historyBookFactory = new HistoryBookFactory();

        Book livre1 = adventureBookFactory.createAventureBook("autheur1", "livre1", 200);
        Book livre2 = adventureBookFactory.createAventureBook("autheur2", "livre2", 220);
        Book livre3 = sfBookFactory.createSfBook("autheur3", "livre3", 230);
        Book livre4 = historyBookFactory.createHistoryBook("autheur4", "livre4", 230);
        //Book livre3 = new Book("autheur3", "livre3", 240, "Litterature", "LIT-0001");
        livre1.displayInformation();
        livre2.displayInformation();
        livre3.displayInformation();
        livre4.displayInformation();*/
        //livre1.displayInformation();
    }
}
