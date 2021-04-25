package com.happy.sky.design.singleton;

/**
 * @name: Singleton <tb>
 * @title: 单列设计模式-饿汉式写法  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 10:53 <tb>
 */
public class Singleton2 {

    private static Singleton2 instance = new Singleton2();


    private Singleton2 (){}

    public static Singleton2 getInstance() {
        return instance;
    }

}
