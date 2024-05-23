package org.example.exception;

public class BadDateException  extends Exception {
    public BadDateException() {
    }

    public BadDateException(String message) {
        super(message);
    }
}
