package com.ru.prac5.service;

import com.google.gson.Gson;
import com.ru.prac5.model.Book;
import com.ru.prac5.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public String createBook(Book book) {
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        if (existingBook.isPresent()) {
            return "error";
        }
        bookRepository.save(book);
        return "created";
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAllByOrderByIdAsc();
    }

    public Book getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        return null;
    }

    public String getBookByIdJson(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Gson gson = new Gson();
            String json = gson.toJson(book.get());
            return json;
        }
        return "error";
    }

    public String updateBook(int id, Book bookDetails) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book existingBook = book.get();
            existingBook.replace(bookDetails);
            bookRepository.save(existingBook);
            return "updated";
        }
        return "error";
    }

    public String deleteBook(int id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            bookRepository.deleteById(id);
            return "deleted";
        }
        return "error";
    }

    public int getAmount(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get().getAmount();
        }
        return -1;
    }

    public void updateAmount(int id, int amount) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            book.get().setAmount(amount);
            bookRepository.save(book.get());
        }
    }
}
