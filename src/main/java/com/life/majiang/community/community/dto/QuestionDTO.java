package com.life.majiang.community.community.dto;

import com.life.majiang.community.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String description;
    private String tag;
    private long gmtCreate;
    private long gmtModify;
    private int creator;
    private int viewCount;
    private int commentCount;
    private int likeCount;
    private User user;
}
