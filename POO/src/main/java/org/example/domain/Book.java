package org.example.domain;

public class Book {

    private String author;
    private String name;
    private int pageNumber;
    private String category;
    private String code;

    public Book(String author, String name, int pageNumber, String category, String code) {
        this.author = author;
        this.name = name;
        this.pageNumber = pageNumber;
        this.category = category;
        this.code = code;
    }

    public void displayLessInformation(int index) {
        System.out.println(
                          "index" + index
                         + " | Auteur: " +  this.author
                         + " | name: " +  this.name
                         + " | pageNumber: " +  this.pageNumber
                         + " | category: " +  this.category
                         + " | code: " +  this.code
        ) ;
    }

    public void displayInformation() {
        System.out.println(
                        "Auteur: " +  this.author + "\n"
                        + "name: " +  this.name + "\n"
                        + "pageNumber: " +  this.pageNumber + "\n"
                        + "category: " +  this.category + "\n"
                        + "code: " +  this.code + "\n"
        ) ;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
