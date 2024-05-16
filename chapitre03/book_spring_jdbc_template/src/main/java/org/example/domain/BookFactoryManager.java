package org.example.domain;

import org.springframework.stereotype.Component;

@Component
public class BookFactoryManager {

    AdventureBookFactory adventureBookFactory;
    SfBookFactory sfBookFactory;
    HistoryBookFactory historyBookFactory;
    LiteratureBookFactory literatureBookFactory;

    public BookFactoryManager() {
        this.adventureBookFactory = new AdventureBookFactory();
        this.sfBookFactory = new SfBookFactory();
        this.historyBookFactory = new HistoryBookFactory();
        this.literatureBookFactory = new LiteratureBookFactory();
    }

    public Book createBook(Integer id, String author, String name, int pageNumber, String type) {
        Book book = null;

        switch (type) {
            case "ADV":
                book = adventureBookFactory.createAventureBook(id, author, name,pageNumber);
                break;
            case "SF":
                book = sfBookFactory.createSfBook(id, author, name,pageNumber);
                break;
            case "HST":
                book = historyBookFactory.createHistoryBook(id,  author, name,pageNumber);
                break;
            default:
                book = literatureBookFactory.createLiteratureBook(id, author, name,pageNumber);
                break;

        }
        return book;
    }

}
