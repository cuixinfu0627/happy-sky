package com.happy.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @name: AppInfo <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/21 10:43 <tb>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo {
    /** App id */
    private String appId;
    /** API 秘钥 */
    private String key;
}
