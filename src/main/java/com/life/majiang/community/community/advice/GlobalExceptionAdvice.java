package com.life.majiang.community.community.advice;

import com.alibaba.fastjson.JSON;
import com.life.majiang.community.community.dto.ResultDto;
import com.life.majiang.community.community.exception.CustomizeErrorCode;
import com.life.majiang.community.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice  //带有此注解会扫描所有的@RequestMapping,@GetMapping,@PostMapping等等
public class GlobalExceptionAdvice {
    @ExceptionHandler(Exception.class)  //配置全局异常处理
    public ModelAndView handleException(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response){

        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            ResultDto resultDto;
            //返回json
            if(e instanceof CustomizeException){   //如果抛出了自己定义的通用业务异常
                resultDto =  ResultDto.errorOf((CustomizeException) e);
            }
            else{    //不是自己定义的通用业务异常
                resultDto =  ResultDto.errorOf(CustomizeErrorCode.Server_Error);
            }
            try {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;

        }
        else{
            //错误页面跳转
            ModelAndView modelAndView = new ModelAndView();
            if(e instanceof CustomizeException){   //如果抛出了自己定义的通用业务异常
                model.addAttribute("message",e.getMessage());
            }
            else{    //不是自己定义的通用业务异常
                model.addAttribute("message",CustomizeErrorCode.Server_Error.getMessage());
            }

            modelAndView.setViewName("/error");
            return modelAndView;
        }


    }

}
