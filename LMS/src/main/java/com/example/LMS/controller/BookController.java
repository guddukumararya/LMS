package com.example.LMS.controller;

import com.example.LMS.entity.Book;
import com.example.LMS.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteById(id);
    }

    @PostMapping("/borrow/{bookId}/{userId}")
    public Book borrowBook(@PathVariable long bookId, @PathVariable long userId) {
        return bookService.borrow(bookId, userId);
    }

    @PostMapping("/return/{bookId}")
    public Book returnBook(@PathVariable long bookId) {
        return bookService.returnBook(bookId);
    }
}