package org.example.domain;

public class AdventureBookFactory {

    public int count = 0;

    public AdventureBookFactory() {
    }

    public Book createAventureBook(String author, String name, int pageNumber) {
        String category = "Adventure";
        String code = "ADV-" + (++count);

        return new Book(author, name, pageNumber, category, code);
    }

}
