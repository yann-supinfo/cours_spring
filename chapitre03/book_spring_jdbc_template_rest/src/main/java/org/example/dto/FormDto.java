package org.example.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class FormDto {


    String title;

    String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FormDto(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
