package org.example.exception;

public class TeamAlreadyExistException extends Exception {


    public TeamAlreadyExistException() {
    }

    public TeamAlreadyExistException(String message) {
        super(message);
    }
}
