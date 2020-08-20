package com.happy.sky.concurrent;

/**
 * @name: ExtendsThread <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/11 14:29 <tb>
 */
public class ExtendsThread extends Thread{
    @Override
    public void run() {
        System.out.println("'用Thread类实现线程'");
    }
}
