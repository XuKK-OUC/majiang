package com.life.majiang.community.community.controller;

import com.life.majiang.community.community.dto.PageDto;
import com.life.majiang.community.community.dto.QuestionDTO;
import com.life.majiang.community.community.mapper.QuestionMapper;
import com.life.majiang.community.community.mapper.UserMapper;
import com.life.majiang.community.community.model.Question;
import com.life.majiang.community.community.model.User;
import com.life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController {

/*    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "world", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "index";

    }*/

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    /**
     * 默认跳转到index页面
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page, //当前页数
                        @RequestParam(name = "size",defaultValue = "5")Integer size  //每页页数
                        ) {
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
        return "indexold";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                    PageDto pagenation = questionService.list(page,size);
                    model.addAttribute("pagenation",pagenation);
                }
                break;
            }
        }

       // PageDto pagenation = questionService.list(page,size);
        //model.addAttribute("pagenation",pagenation);

        return "index";
    }


    /**
     *用户登录验证
     * @param request
     * @return
     */
  /*  @GetMapping("/index2")
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
    }*/
}
