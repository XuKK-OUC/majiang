package com.life.majiang.community.community.mapper;

import com.life.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modify,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModify},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") int offset,@Param("size") Integer size);

    @Select("select count(1) from question")
    int count();
}
