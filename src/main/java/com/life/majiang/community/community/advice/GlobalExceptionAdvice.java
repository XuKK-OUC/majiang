package com.life.majiang.community.community.advice;

import com.life.majiang.community.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice  //带有此注解会扫描所有的@RequestMapping,@GetMapping,@PostMapping等等
public class GlobalExceptionAdvice {
    @ExceptionHandler(Exception.class)  //配置全局异常处理
    public ModelAndView handleException(Throwable e, Model model){
        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof CustomizeException){   //如果抛出了自己定义的通用业务异常
            model.addAttribute("message",e.getMessage());
        }
        else{    //不是自己定义的通用业务异常
            model.addAttribute("message","系统异常");
        }

        modelAndView.setViewName("/error");
        return modelAndView;

    }

}
