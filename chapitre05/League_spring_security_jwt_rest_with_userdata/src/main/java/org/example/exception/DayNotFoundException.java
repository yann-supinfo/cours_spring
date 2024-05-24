package org.example.exception;

public class DayNotFoundException extends Exception {
    public DayNotFoundException() {
    }

    public DayNotFoundException(String message) {
        super(message);
    }
}
