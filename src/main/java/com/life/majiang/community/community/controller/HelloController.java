package com.life.majiang.community.community.controller;

import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name",defaultValue = "world",required = false)String name, Model model){
        model.addAttribute("name",name);
        return "greeting";

    }
}
