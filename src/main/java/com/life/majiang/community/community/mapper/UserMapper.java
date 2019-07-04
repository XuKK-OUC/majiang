package com.life.majiang.community.community.mapper;

import com.life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into USER(NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFY) values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modify})")
    void insert(User user);
}
