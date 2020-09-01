package com.happy.sky.concurrent.thread9.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @name: DontVolatile <tb>
 * @title: volatile 的适用场合
 *         不适用：a++  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/27 17:03 <tb>
 */
public class DontVolatile implements Runnable {

    volatile int a;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r =  new DontVolatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((DontVolatile) r).a);
        System.out.println(((DontVolatile) r).realA.get());
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}
