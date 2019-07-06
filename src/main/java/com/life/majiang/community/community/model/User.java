package com.life.majiang.community.community.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String accountId;
    private String token;
    private long gmtCreate;
    private long gmtModify;
    private String avatarUrl; //图像地址


}
