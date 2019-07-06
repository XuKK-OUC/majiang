package com.life.majiang.community.community.controller;

import com.life.majiang.community.community.dto.PageDto;
import com.life.majiang.community.community.mapper.UserMapper;
import com.life.majiang.community.community.model.User;
import com.life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class profileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1")Integer page, //当前页数
                          @RequestParam(name = "size",defaultValue = "5")Integer size ) {//每页页数
       /* Cookie[] cookies = request.getCookies();
        if(cookies==null){   //尚未登录过
            return "indexold";
        }
        User user=null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                 user = userMapper.findByToken(token);
                if(user != null){  //登录过
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if(user==null){
            return "indexold";
        }*/
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "indexold";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PageDto pageDto = questionService.listByUserId(user.getId(),page,size);
        model.addAttribute("pagenation",pageDto);
        return "profile";
    }
}
