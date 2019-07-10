package com.life.majiang.community.community.exception;
//自己定义的通用业务逻辑异常
public class CustomizeException extends RuntimeException {
    private  Integer code;
    private String message;
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code=errorCode.getcode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
