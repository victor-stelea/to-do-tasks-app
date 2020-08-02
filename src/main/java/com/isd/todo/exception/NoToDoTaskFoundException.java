package com.isd.todo.exception;

public class NoToDoTaskFoundException extends Exception {
    public NoToDoTaskFoundException (String exceptionMessage) {
        super(exceptionMessage);
    }
}
