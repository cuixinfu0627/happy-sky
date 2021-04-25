package com.happy.sky.design.factory;

/**
 * @name: Benz <tb>
 * @title: 具体实现类  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 11:43 <tb>
 */
class Benz extends Car {
    public void run() {
        System.out.println("Benz开始启动了。。。。。");
    }

    public void stop() {
        System.out.println("Benz停车了。。。。。");
    }
}