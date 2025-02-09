package com.example.LMS.service;

import com.example.LMS.entity.Book;
import com.example.LMS.entity.User;
import com.example.LMS.exception.BookNotFoundException;
import com.example.LMS.exception.UserNotFoundException;
import com.example.LMS.exception.BookAlreadyBorrowedException;
import com.example.LMS.exception.BookNotBorrowedException;
import com.example.LMS.repository.BookRepository;
import com.example.LMS.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }

    public Book update(long id, Book book) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    public Book borrow(long bookId, long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + bookId));

        if (book.isBorrowed()) {
            throw new BookAlreadyBorrowedException("Book is already borrowed!");
        }

        book.setBorrowedBy(user);
        book.setBorrowed(true);
        return bookRepository.save(book);
    }

    public Book returnBook(long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + bookId));

        if (!book.isBorrowed()) {
            throw new BookNotBorrowedException("Book is not currently borrowed!");
        }

        book.setBorrowedBy(null);
        book.setBorrowed(false);
        return bookRepository.save(book);
    }
}