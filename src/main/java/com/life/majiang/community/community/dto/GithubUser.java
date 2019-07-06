package com.life.majiang.community.community.dto;

import lombok.Data;

/**
 * 返回用户登录信息的bean
 * 使用@Data注解:@Data注解在类上，会为类的所有属性自动生成setter/getter、equals、canEqual、hashCode、toString方法，
 * 如为final属性，则不会为该属性生成setter方法。
 */
@Data
public class GithubUser {
    private String login;
    private long id;
    //private String avatar_url;
    private String avatarUrl;  //fastjson 可以自动把下划线标示的属性映射到驼峰标示的属性

}
