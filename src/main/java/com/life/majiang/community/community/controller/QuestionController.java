package com.life.majiang.community.community.controller;

import com.life.majiang.community.community.dto.QuestionDTO;
import com.life.majiang.community.community.mapper.QuestionMapper;
import com.life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/ques/{id}")
    public String quesiton(@PathVariable(name = "id") int id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
