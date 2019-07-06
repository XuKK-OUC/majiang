package com.life.majiang.community.community.service;

import com.life.majiang.community.community.mapper.UserMapper;
import com.life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
      User dbUser =   userMapper.findByAccountId(user.getAccountId());
      if(dbUser==null){ //插入
          user.setGmtCreate(System.currentTimeMillis());
          user.setGmtModify(user.getGmtCreate());
          userMapper.insert(user);
      }else{
            //更新
          dbUser.setGmtModify(System.currentTimeMillis());
          dbUser.setAvatarUrl(user.getAvatarUrl());
          dbUser.setName(user.getName());
          dbUser.setToken(user.getToken());
          userMapper.update(dbUser);
      }
    }
}
