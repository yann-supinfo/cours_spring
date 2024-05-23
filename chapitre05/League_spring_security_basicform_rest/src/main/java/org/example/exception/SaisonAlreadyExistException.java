package org.example.exception;

public class SaisonAlreadyExistException extends Exception {
    public SaisonAlreadyExistException() {
    }

    public SaisonAlreadyExistException(String message) {
        super(message);
    }
}
