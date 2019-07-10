package com.life.majiang.community.community.service;

import com.life.majiang.community.community.enums.CommentTypeEnum;
import com.life.majiang.community.community.exception.CustomizeErrorCode;
import com.life.majiang.community.community.exception.CustomizeException;
import com.life.majiang.community.community.mapper.CommentMapper;
import com.life.majiang.community.community.mapper.QuestionExtMapper;
import com.life.majiang.community.community.mapper.QuestionMapper;
import com.life.majiang.community.community.model.Comment;
import com.life.majiang.community.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

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
}
