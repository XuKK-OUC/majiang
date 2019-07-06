package com.life.majiang.community.community.mapper;

import com.life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into USER(NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFY,AVATAR_URL) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModify},#{avatarUrl})")
    void insert(User user);

    @Select("select * from USER where TOKEN = #{token}")
    User findByToken(String token);

    @Select("select * from USER where id = #{id}")
    User findUserById(@Param("id") int id);

    @Select("select * from USER where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name},token=#{token},gmt_modify=#{gmtModify},AVATAR_URL=#{avatarUrl} where id=#{id}")
    void update(User dbUser);
}
