package com.demo.exception;

public class IntegerException extends MyException{
    public IntegerException() {
        super();
    }

    public IntegerException(String message) {
        super(message);
    }

    public IntegerException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegerException(Throwable cause) {
        super(cause);
    }
}
