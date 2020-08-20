package com.happy.sky.concurrent;

/**
 * @name: RunnableThread <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/11 14:28 <tb>
 */
public class RunnableThread implements Runnable{
    @Override
    public void run() {
        System.out.println("'用实现Runnable接口实现线程'");
    }
}
