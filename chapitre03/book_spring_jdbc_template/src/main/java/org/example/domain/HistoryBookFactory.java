package org.example.domain;

public class HistoryBookFactory {

    public int count = 0;

    public HistoryBookFactory() {
    }

    public Book createHistoryBook(Integer id, String author, String name, int pageNumber) {
        String category = "History";
        String code = "HST-" + (++count);

        return new Book(id, author, name, pageNumber, category, code);
    }
}
