package com.life.majiang.community.community.mapper;

import com.life.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modify,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModify},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") int offset,@Param("size") Integer size);

    @Select("select count(1) from question")
    int count();
    @Select("select * from question where creator= #{userid} limit #{offset},#{size}")
    List<Question> listByUserId(@Param("userid") int userid,@Param("offset") int offset,@Param("size") Integer size);

    @Select("select count(1) from question where creator=#{userid}")
    int countByUserId(@Param("userid") int userid);

    @Select("select * from question where id= #{id}")
    Question getById(@Param("id") int id);

    @Update("update question set title=#{title},description=#{description},gmt_modify=#{gmtModify},tag=#{tag} where id=#{id}")
    void update(Question question);
}
