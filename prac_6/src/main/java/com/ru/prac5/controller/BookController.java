package com.ru.prac5.controller;

import com.ru.prac5.model.Book;
import com.ru.prac5.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/book")
    public String postBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping(value = "/book/{id}")
    public String getBook(@PathVariable int id) {
        return bookService.getBookByIdJson(id);
    }

    @PutMapping(value = "book/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping(value = "/book/{id}")
    public String deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }




}