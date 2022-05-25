package com.woniumall.exception;

public class UsernameOrPasswordEorrException extends MyServiceException{
    public UsernameOrPasswordEorrException() {
        super();
    }

    public UsernameOrPasswordEorrException(String message) {
        super(message);
    }

    public UsernameOrPasswordEorrException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameOrPasswordEorrException(Throwable cause) {
        super(cause);
    }
}
