package com.happy.sky.entity.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @name: ApiResult <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/21 10:45 <tb>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {

    /** 代码 */
    private String code;

    /** 结果 */
    private String msg;
}