package com.life.majiang.community.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    Question_Not_Found("问题找不到");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
