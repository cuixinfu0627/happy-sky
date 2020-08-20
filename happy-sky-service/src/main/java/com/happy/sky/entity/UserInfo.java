package com.happy.sky.entity;

import lombok.Data;

/**
 * @name: UserInfo <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/21 10:44 <tb>
 */
@Data
public class UserInfo {
    /** 用户名 */
    private String username;
    /** 手机号 */
    private String mobile;
    /** 邮箱 */
    private String email;
    /** 密码 */
    private String password;
    /** 盐 */
    private String salt;

    private AccessToken accessToken;

    public UserInfo(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }
}
