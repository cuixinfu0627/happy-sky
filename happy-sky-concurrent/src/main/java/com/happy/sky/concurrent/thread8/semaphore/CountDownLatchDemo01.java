package com.happy.sky.concurrent.thread8.semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @name: CountDownLatchDemo01 <tb>
 * @title: CountDownLatch 是如何安排线程执行顺序的。* 多个线程等待某一个线程的信号，同时开始执行  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 16:55 <tb>
 */
public class CountDownLatchDemo01 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("运动员有5秒的准备时间");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(no + "号运动员准备完毕，等待裁判员的发令枪");
                    try {
                        countDownLatch.await();
                        System.out.println(no + "号运动员开始跑步了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("5秒准备时间已过，发令枪响，比赛开始！");
        countDownLatch.countDown();
    }
}
