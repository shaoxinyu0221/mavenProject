package com.demo.exception;

public class UserNullException extends MyException{
    public UserNullException() {
        super();
    }

    public UserNullException(String message) {
        super(message);
    }

    public UserNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNullException(Throwable cause) {
        super(cause);
    }
}
