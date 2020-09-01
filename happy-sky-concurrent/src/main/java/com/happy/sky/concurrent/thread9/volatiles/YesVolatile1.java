package com.happy.sky.concurrent.thread9.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @name: YesVolatile1 <tb>
 * @title: volatile 的适用场合
 *         适用场合1：布尔标记位  <tb>
 *         适用场合 2：作为触发器 <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/27 17:03 <tb>
 */
public class YesVolatile1 implements Runnable {

    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r =  new YesVolatile1();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((YesVolatile1) r).done);
        System.out.println(((YesVolatile1) r).realA.get());
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        done = true;
    }
}
