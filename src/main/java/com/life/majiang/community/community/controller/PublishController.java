package com.life.majiang.community.community.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    //跳转到发布问题页面
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    //发布问题
    @PostMapping("/doPublish")
    public String doPublish(@RequestParam("title")String title,
                            @RequestParam("description")String description,
                            @RequestParam("tag")String tag,
                            @RequestParam("id")int id,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null || title==""){
            model.addAttribute("error","问题标题不能为空");
            return "publish";
        }if(description==null || description==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }if(tag==null || tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        System.out.println("进入发布方法");
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        System.out.println("得到cookies的个数"+cookies.length);
        User user = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                System.out.println(user);
                //1af6843d-f3eb-4363-beb4-7004362c9983
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if(user==null){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        System.out.println(user.getAvatarUrl());
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModify(question.getGmtCreate());
        question.setId(id);
        questionService.createOrUpdate(question);
        //questionMapper.create(question);
        return "redirect:/";
    }

    //跳转到编辑问题页面
    @GetMapping("publish/{id}")
    public String edit(@PathVariable("id") int id,Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());

        return "publish";
    }
}
