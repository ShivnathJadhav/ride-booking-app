package com.springboot.project.uber.uberapp.exceptions;

public class RuntimeConflictsException extends RuntimeException{
    public RuntimeConflictsException() {
    }

    public RuntimeConflictsException(String message) {
        super(message);
    }
}
