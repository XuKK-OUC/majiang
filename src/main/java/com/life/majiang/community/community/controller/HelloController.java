package com.life.majiang.community.community.controller;

import com.life.majiang.community.community.mapper.UserMapper;
import com.life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

/*    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "world", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "index";

    }*/

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index2")
    public String index2(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        return "index";
    }

}
