package com.life.majiang.community.community.dto;

/**
 * 返回用户登录信息的bean
 */
public class GithubUser {
    private String login;
    private long id;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
