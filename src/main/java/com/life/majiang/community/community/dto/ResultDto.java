package com.life.majiang.community.community.dto;

import com.life.majiang.community.community.exception.CustomizeErrorCode;
import com.life.majiang.community.community.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDto {
    private int code;
    private String message;

    public static ResultDto errorOf(Integer code,String message){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeErrorCode customizeErrorCode) {
        return  errorOf(customizeErrorCode.getcode(),customizeErrorCode.getMessage());
    }
    public static  ResultDto okof(){
        return  errorOf(200,"请求成功ok");
    }

    public static ResultDto errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }
}
