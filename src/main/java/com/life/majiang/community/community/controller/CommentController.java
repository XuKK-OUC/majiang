package com.life.majiang.community.community.controller;

import com.life.majiang.community.community.dto.CommentDto;
import com.life.majiang.community.community.dto.ResultDto;
import com.life.majiang.community.community.exception.CustomizeErrorCode;
import com.life.majiang.community.community.mapper.CommentMapper;
import com.life.majiang.community.community.model.Comment;
import com.life.majiang.community.community.model.User;
import com.life.majiang.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDto commentDto, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDto.errorOf(CustomizeErrorCode.NO_Login);
        }
        Comment comment = new Comment();
        comment.setPraentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModify(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentor(1L);
        commentService.insert(comment);
        return ResultDto.okof();
    }
}
