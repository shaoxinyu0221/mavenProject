package com.woniumall.exception;

public class AccountIsExist extends MyServiceException{
    public AccountIsExist() {
        super();
    }

    public AccountIsExist(String message) {
        super(message);
    }

    public AccountIsExist(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountIsExist(Throwable cause) {
        super(cause);
    }
}
