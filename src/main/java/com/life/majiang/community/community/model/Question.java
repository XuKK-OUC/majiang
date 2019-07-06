package com.life.majiang.community.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private long gmtCreate;
    private long gmtModify;
    private int creator;
    private int viewCount;
    private int commentCount;
    private int likeCount;


}
