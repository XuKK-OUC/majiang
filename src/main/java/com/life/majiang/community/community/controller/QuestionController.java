package com.life.majiang.community.community.controller;

import com.life.majiang.community.community.dto.CommentDto;
import com.life.majiang.community.community.dto.QuestionDTO;
import com.life.majiang.community.community.mapper.QuestionMapper;
import com.life.majiang.community.community.service.CommentService;
import com.life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    CommentService commentService;
    @GetMapping("/ques/{id}")
    public String quesiton(@PathVariable(name = "id") long id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
       List<CommentDto> comments =  commentService.listByQuestionId(id);
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
