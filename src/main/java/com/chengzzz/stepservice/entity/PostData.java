package com.chengzzz.stepservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 提交的请求体
 * </p>
 *
 * @author 等什么柠檬君
 * @since 2020/8/21
 */

public class PostData {
    String password;
    String clientId ="8e844e28db7245eb81823132464835eb";
    Integer appType = 6;
    String loginName;
    Integer roleType = 0;

    public PostData( String loginName,String password) {
        this.password = password;
        this.loginName = loginName;
    }


}
