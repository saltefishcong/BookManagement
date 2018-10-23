package com.example.bookmanagement.eity;

public class TransException extends RuntimeException {

    public TransException() {
    }

    public TransException(String message) {
        super(message);
    }
}
