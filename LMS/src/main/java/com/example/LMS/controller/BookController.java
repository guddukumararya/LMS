package com.example.LMS.controller;

import com.example.LMS.model.Book;
import com.example.LMS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
    @GetMapping("/getBookById")
    public Optional<Book> getBook(long id) {
        return bookService.findById(id);
    }
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }
    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.save(book);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteById(id);
    }

    @PostMapping("/borrowBook/{bookId}/{userId}")
    public Book borrowBook(@PathVariable long bookId,@PathVariable long userId) {
        System.out.println("hello");
        return bookService.borrow(bookId, userId);
    }
    @PostMapping("/returnBook/{bookId}")
    public Book returnBook(@PathVariable long bookId) {
        return bookService.returnBook(bookId);
    }
}
