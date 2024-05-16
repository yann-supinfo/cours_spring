package org.example.application.controller;

import org.example.application.service.BookService;
import org.example.domain.Book;
import org.example.dto.FormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private  BookService bookService;

    @GetMapping("/get")
    public String get() {
        return "{\"message\": \"GET controller called\"}";
    }

    @GetMapping("/getAll")
    public List<Book> getAll() {
        return bookService.getBooks();
    }

    @PostMapping("/post")
    public String post(@RequestBody FormDto formDto) {
        System.out.println("----- post controller ------");
        System.out.println(formDto.getTitle() + " | " + formDto.getAuthor());
        System.out.println("----- post controller ------");
        bookService.addBook(formDto.getTitle(), formDto.getAuthor());
        return "{\"message\": \"POST controller called\"}";
    }

    @PutMapping("/update")
    public String update() {
        return "{\"message\": \"UPDATE controller called\"}";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "{\"message\": \"DELETE controller called\"}";
    }

}
