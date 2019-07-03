package com.life.majiang.community.community.controller;

import com.life.majiang.community.community.dto.AccessTokenDTO;
import com.life.majiang.community.community.dto.GithubUser;
import com.life.majiang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    /**
     * 用户点击登录按钮后,从github上认证后,就返回到callback中
     * 根据认证得到的code和state再调用access_token接口获得accesstoken,最后在使用accesstoken调用user接口获得登录信息
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,@RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8087/callback");
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id("a5ab359c706921bbbfc6");
        accessTokenDTO.setClient_secret("8100c028cdce14aa88a68eafba8169224db5bfbf");
        //获得accesstoken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //获得用户的登录信息
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getLogin());
        System.out.println(user.getId());

        return "index";
    }
}
