package com.life.majiang.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.life.majiang.community.community.dto.AccessTokenDTO;
import com.life.majiang.community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    /**
     *
     根据code值获取accessToken的值
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println("获取到的accessToken是" + string);
            String[] split = string.split("&");
            String token = split[0];
            String accesstoken = token.split("=")[1];
            System.out.println(accesstoken);
            return accesstoken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     根据accessToken得到用户登录信息
     */
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
           String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);  // string中的avatar_url--> GithubUser中的avatarUrl  fastjson 可以自动把下划线标示的属性映射到驼峰标示的属性
            return  githubUser;
        }catch (IOException e){

        }
        return null;
        }
    }
