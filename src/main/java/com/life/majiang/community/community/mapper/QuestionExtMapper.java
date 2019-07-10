package com.life.majiang.community.community.mapper;

import com.life.majiang.community.community.model.Question;
import com.life.majiang.community.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    void incView(Question question);
    int incCommentCount(Question record);
}