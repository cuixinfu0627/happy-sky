package com.happy.sky.design.singleton;

/**https://mp.weixin.qq.com/s?__biz=MzIwMTY0NDU3Nw==&mid=2651938221&idx=1&sn=9cb29d1eb0fdbdb5f976306b08d5bdcc&chksm=8d0f32e3ba78bbf547c6039038682706a2eaf83002158c58060d5eb57bdd83eb966a1e223ef6&scene=21#wechat_redirect
 * @name: Singleton <tb>
 * @title: 懒汉式写法（线程安全）  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 10:53 <tb>
 * 简单点说，就是一个应用程序中，某个类的实例对象只有一个，你没有办法去new，因为构造器是被private修饰的，一般通过getInstance()的方法来获取它们的实例。
 * getInstance()的返回值是一个对象的引用，并不是一个新的实例，所以不要错误的理解成多个对象。单例模式实现起来也很容易，直接看demo吧
 */
public class Singleton1 {

    private static Singleton1 instance;

    private Singleton1() {
    }

    public static synchronized Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println("hello java singleton design model");
        Singleton1 instance = getInstance();
        System.out.println(instance);
    }
}
