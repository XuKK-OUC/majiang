package com.life.majiang.community.community.exception;
//自己定义的通用业务逻辑异常
public class CustomizeException extends RuntimeException {
    private String message;
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
