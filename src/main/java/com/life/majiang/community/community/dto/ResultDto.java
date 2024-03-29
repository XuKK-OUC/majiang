package com.life.majiang.community.community.dto;

import com.life.majiang.community.community.exception.CustomizeErrorCode;
import com.life.majiang.community.community.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDto<T> {
    private int code;
    private String message;
    private T data;
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
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }
    public static <T>  ResultDto okof(T t){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        resultDto.setData(t);
        return  resultDto;
    }

    public static ResultDto errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }
}
