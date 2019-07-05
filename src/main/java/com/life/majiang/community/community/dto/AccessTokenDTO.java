package com.life.majiang.community.community.dto;

import lombok.Data;

/**
 * AccessTokenDTO 调用access_token接口的请求参数
 * 引入lombok不需要再写getter/setter方法
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code	;
    private String redirect_uri;
    private String state;


}
