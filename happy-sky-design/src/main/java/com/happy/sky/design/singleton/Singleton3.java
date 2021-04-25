package com.happy.sky.design.singleton;

/**
 * @name: Singleton3 <tb>
 * @title: 单列设计模式-双重校验锁  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 10:53 <tb>
 */
public class Singleton3 {

    private volatile static Singleton3 singleton;

    private Singleton3 (){}

    public static Singleton3 getSingleton3() {
        if (singleton == null) {
            synchronized (Singleton3.class) {
                if (singleton == null) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }

}
