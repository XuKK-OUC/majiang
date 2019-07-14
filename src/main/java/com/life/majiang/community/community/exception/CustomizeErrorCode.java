package com.life.majiang.community.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    Question_Not_Found(400,"问题找不到"),
    Target_Parram_Not_Found(401,"未选中任何问题或评论进行回复"),
    NO_Login(203,"当前操作需要用户登录,请先登录"),
    Server_Error(500,"服务端错误"),
    Type_Error_Wrong(501,"评论类型错误或不存在"),
    Comment_Not_Found(502,"评论不存在"),
    Comment_Is_Empty(205,"评论内容为空");
    private String message;
    private Integer code;
    CustomizeErrorCode(Integer code,String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Integer getcode() {
        return code;
    }
}
