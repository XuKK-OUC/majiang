package com.life.majiang.community.community.controller;

import com.life.majiang.community.community.dto.AccessTokenDTO;
import com.life.majiang.community.community.dto.GithubUser;
import com.life.majiang.community.community.mapper.UserMapper;
import com.life.majiang.community.community.model.User;
import com.life.majiang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client_id}")
     private String clientId;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_uri}")
    private  String redirect_uri;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户点击登录按钮后,从github上认证后,就返回到callback中
     * 根据认证得到的code和state再调用access_token接口获得accesstoken,最后在使用accesstoken调用user接口获得登录信息
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);   //将各种信息存放到application.properties
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(client_secret);
        //获得accesstoken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //获得用户的登录信息
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getLogin());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modify(user.getGmt_create());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/index2";  //redirect:/index, 找的是controller中地址映射为 /index的方法
        }
        else{
            //登录失败,重新登录
            return "redirect:/index2";
        }


    }
}
