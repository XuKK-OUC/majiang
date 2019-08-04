package com.life.majiang.community.community.controller;

import com.life.majiang.community.community.dto.CommentCreateDto;
import com.life.majiang.community.community.dto.CommentDto;
import com.life.majiang.community.community.dto.ResultDto;
import com.life.majiang.community.community.enums.CommentTypeEnum;
import com.life.majiang.community.community.exception.CustomizeErrorCode;
import com.life.majiang.community.community.model.Comment;
import com.life.majiang.community.community.model.User;
import com.life.majiang.community.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentCreateDto commentCreateDto, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDto.errorOf(CustomizeErrorCode.NO_Login);
        }
        if(commentCreateDto == null || StringUtils.isBlank(commentCreateDto.getContent())){ //使用了commons.lang包
        return ResultDto.errorOf(CustomizeErrorCode.Comment_Is_Empty);
        }
        Comment comment = new Comment();
        comment.setPraentId(commentCreateDto.getParentId());
        comment.setContent(commentCreateDto.getContent());
        comment.setType(commentCreateDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModify(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentor(1L);
        commentService.insert(comment);
        return ResultDto.okof();
    }

    @ResponseBody

    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDto<List<CommentDto>> comments(@PathVariable (name = "id")long id){
        List<CommentDto> commentDtos = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDto.okof(commentDtos);
    }

}
