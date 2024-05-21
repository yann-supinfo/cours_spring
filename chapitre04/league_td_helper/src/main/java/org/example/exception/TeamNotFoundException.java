package org.example.exception;

public class TeamNotFoundException extends Exception {
    private String message;

    public TeamNotFoundException() {
    }

    public TeamNotFoundException(String message) {
        super(message);
    }
}
