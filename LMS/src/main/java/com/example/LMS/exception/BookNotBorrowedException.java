package com.example.LMS.exception;

public class BookNotBorrowedException extends RuntimeException{
    public BookNotBorrowedException(String message) {
        super(message);
    }
}
