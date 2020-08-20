package com.happy.sky.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @name: CallableTask <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/11 14:42 <tb>
 */
public class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return new Random().nextInt();
    }

    public static void main(String[] args) {
        //匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });

        //lambdda
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }

}
