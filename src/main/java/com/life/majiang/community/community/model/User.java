package com.life.majiang.community.community.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String account_id;
    private String token;
    private long gmt_create;
    private long gmt_modify;
    private String avatar_url; //图像地址


}
