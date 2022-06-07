package com.woniumall.exception;

public class GoodsInThisCategoryId extends MyServiceException {
    public GoodsInThisCategoryId() {
        super();
    }

    public GoodsInThisCategoryId(String message) {
        super(message);
    }

    public GoodsInThisCategoryId(String message, Throwable cause) {
        super(message, cause);
    }

    public GoodsInThisCategoryId(Throwable cause) {
        super(cause);
    }
}
