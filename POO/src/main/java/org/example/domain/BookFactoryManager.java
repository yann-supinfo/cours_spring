package org.example.domain;

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

    public Book createBook(String author, String name, int pageNumber, String type) {
        Book book = null;

        switch (type) {
            case "ADV":
                book = adventureBookFactory.createAventureBook(author, name,pageNumber);
                break;
            case "SF":
                book = sfBookFactory.createSfBook(author, name,pageNumber);
                break;
            case "HST":
                book = historyBookFactory.createHistoryBook(author, name,pageNumber);
                break;
            default:
                book = literatureBookFactory.createLiteratureBook(author, name,pageNumber);
                break;

        }
        return book;
    }

}
