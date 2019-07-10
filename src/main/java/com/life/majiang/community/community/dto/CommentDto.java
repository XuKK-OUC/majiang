package com.life.majiang.community.community.dto;

import lombok.Data;

@Data
public class CommentDto {
    private long parentId;
    private String content;
    private Integer type;
}
