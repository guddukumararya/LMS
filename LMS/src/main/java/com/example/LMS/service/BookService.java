package com.example.LMS.service;

import com.example.LMS.model.Book;
import com.example.LMS.model.User;
import com.example.LMS.repository.BookRepository;
import com.example.LMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public Optional<Book> findById(long id){
        return bookRepository.findById(id);
    }
    public Book save(Book book){

        System.out.println("=====in save book====");
        return bookRepository.save(book);
    }
    public void deleteById(long id){
        bookRepository.deleteById(id);
    }
    public Book update(Book book){
        return bookRepository.save(book);
    }
    public Book borrow(long id, long userId){
        User user = userRepository.findById(userId).get();

        Book book = findById(id).get();
        System.out.println("=====in borrow book===="+ book.getAuthor());
        if(bookRepository.existsById(id) && userRepository.existsById(userId) && !book.isBorrowed()){
            book.setBorrowedby(user);
            book.setBorrowed(true);
            return save(book);
        }
        return null ;
    }
    public Book returnBook(long id){
        Book book = findById(id).get();
        if(bookRepository.existsById(id) && book.isBorrowed()){
            book.setBorrowedby(null);
            book.setBorrowed(false);
            return save(book);
        }
        return null;
    }

}