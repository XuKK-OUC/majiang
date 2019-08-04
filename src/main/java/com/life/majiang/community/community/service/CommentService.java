package com.life.majiang.community.community.service;

import com.life.majiang.community.community.dto.CommentDto;
import com.life.majiang.community.community.enums.CommentTypeEnum;
import com.life.majiang.community.community.exception.CustomizeErrorCode;
import com.life.majiang.community.community.exception.CustomizeException;
import com.life.majiang.community.community.mapper.CommentMapper;
import com.life.majiang.community.community.mapper.QuestionExtMapper;
import com.life.majiang.community.community.mapper.QuestionMapper;
import com.life.majiang.community.community.mapper.UserMapper;
import com.life.majiang.community.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Transactional //添加事务
    public void insert(Comment comment) {
        if(comment.getPraentId()==null || comment.getPraentId()==0){
            throw  new CustomizeException(CustomizeErrorCode.Target_Parram_Not_Found);
        }
        if(comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw  new CustomizeException(CustomizeErrorCode.Type_Error_Wrong);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getPraentId());
            if(dbComment==null){
                throw  new CustomizeException(CustomizeErrorCode.Comment_Not_Found);
            }
            commentMapper.insert(comment);
        }else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getPraentId());
            if (question==null){
                throw new CustomizeException(CustomizeErrorCode.Question_Not_Found);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }

    public List<CommentDto> listByTargetId(long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andPraentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size()==0){
            return new ArrayList<>();
        }

        //获取去重的评论人
        Set<Long> commentors = comments.stream().map(comment -> comment.getCommentor()).collect(Collectors.toSet());
        List<Long>userIds = new ArrayList<>();
        userIds.addAll(commentors);

        //获取评论人并转化为map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换 comment变为commentDto
        List<CommentDto> commentDtos = comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUser(userMap.get(comment.getCommentor()));
            return commentDto;
        }).collect(Collectors.toList());

        return commentDtos;
    }
}
