package com.happy.sky.concurrent.thread9.volatiles;

/**
 * @name: Singleton <tb>
 * @title: 双重检查锁模式的写法
 * 单例模式有多种写法，我们重点介绍一下和 volatile 强相关的双重检查锁模式的写法，代码如下所示：<tb>
 * 这种写法的优点是不仅线程安全，而且延迟加载、效率也更高。<tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/27 17:12 <tb>
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                    //第一步是给 singleton 分配内存空间；
                    //然后第二步开始调用 Singleton 的构造函数等，来初始化 singleton；
                    //最后第三步，将 singleton 对象指向分配的内存空间（执行完这步 singleton 就不是 null 了）。
                }
            }
        }
        return singleton;
    }
}
