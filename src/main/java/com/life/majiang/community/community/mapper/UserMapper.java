package com.life.majiang.community.community.mapper;

import com.life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into USER(NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFY,AVATAR_URL) values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modify},#{avatarUrl})")
    void insert(User user);

    @Select("select * from USER where TOKEN=#{token}")
    User findByToken(String token);
}
