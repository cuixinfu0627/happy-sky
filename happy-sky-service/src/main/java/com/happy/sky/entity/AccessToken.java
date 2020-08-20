package com.happy.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @name: AccessToken <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/21 10:43 <tb>
 */
@Data
@AllArgsConstructor
public class AccessToken {
    /** token */
    private String token;

    /** 失效时间 */
    private Date expireTime;
}
