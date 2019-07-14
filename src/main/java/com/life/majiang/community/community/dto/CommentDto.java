package com.life.majiang.community.community.dto;

import com.life.majiang.community.community.model.User;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long praentId;
    private Integer type;
    private Long commentor;
    private Long gmtCreate;
    private Long gmtModify;
    private Long likeCount;
    private String content;
    private User user;

}
