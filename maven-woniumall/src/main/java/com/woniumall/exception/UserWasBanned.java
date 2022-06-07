package com.woniumall.exception;

public class UserWasBanned extends MyServiceException{
    public UserWasBanned() {
        super();
    }

    public UserWasBanned(String message) {
        super(message);
    }

    public UserWasBanned(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWasBanned(Throwable cause) {
        super(cause);
    }
}
