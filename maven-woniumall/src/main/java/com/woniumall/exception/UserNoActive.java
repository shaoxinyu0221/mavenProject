package com.woniumall.exception;

public class UserNoActive extends MyServiceException{
    public UserNoActive() {
        super();
    }

    public UserNoActive(String message) {
        super(message);
    }

    public UserNoActive(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNoActive(Throwable cause) {
        super(cause);
    }
}
