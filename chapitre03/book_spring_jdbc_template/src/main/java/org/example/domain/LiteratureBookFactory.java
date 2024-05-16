package org.example.domain;

public class LiteratureBookFactory {
    public int count = 0;

    public LiteratureBookFactory() {
    }

    public Book createLiteratureBook(Integer id, String author, String name, int pageNumber) {
        String category = "Literature";
        String code = "LIT-" + (++count);

        return new Book(id, author, name, pageNumber, category, code);
    }
}
