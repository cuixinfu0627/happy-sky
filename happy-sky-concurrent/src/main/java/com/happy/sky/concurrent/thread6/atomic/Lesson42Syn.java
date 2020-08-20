package com.happy.sky.concurrent.thread6.atomic;

/**
 * @name: Lesson42Syn <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 10:35 <tb>
 */
public class Lesson42Syn implements Runnable {

    static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Lesson42Syn();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(value);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (this) {
                value++;
            }
        }
    }
}