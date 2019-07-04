package com.life.majiang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "world", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "index";

    }

    @GetMapping("/")
    public String index() {
        return "index";
    } @GetMapping("/index")
    public String index2() {
        return "index";
    }

}
