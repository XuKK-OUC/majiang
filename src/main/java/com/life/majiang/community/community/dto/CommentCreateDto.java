package com.life.majiang.community.community.dto;

import lombok.Data;

@Data
public class CommentCreateDto {
    private long parentId;
    private String content;
    private Integer type;
}
