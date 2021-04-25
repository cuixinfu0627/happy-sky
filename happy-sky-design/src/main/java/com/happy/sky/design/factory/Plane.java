package com.happy.sky.design.factory;

/**
 * @name: Plane <tb>
 * @title: 具体产品角色  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 14:15 <tb>
 */
public class Plane implements Moveable {
    @Override
    public void run() {
        System.out.println("plane....");
    }
}