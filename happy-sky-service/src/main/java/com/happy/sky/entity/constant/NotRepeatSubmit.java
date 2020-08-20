package com.happy.sky.entity.constant;

/**
 * @name: NotRepeatSubmit <tb>
 * @title: 自定义注解，防止重复提交。  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/21 10:42 <tb>
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 禁止重复提交
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotRepeatSubmit {
    /** 过期时间，单位毫秒 **/
    long value() default 5000;
}
