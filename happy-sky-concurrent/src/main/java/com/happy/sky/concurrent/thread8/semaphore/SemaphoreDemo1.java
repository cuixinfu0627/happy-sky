package com.happy.sky.concurrent.thread8.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @name: SemaphoreDemo1 <tb>
 * @title: 信号量
 * 信号量的一个最主要的作用就是，来控制那些需要限制并发访问量的资源<tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 16:46 <tb>
 */
public class SemaphoreDemo1 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 1000; i++) {
            service.submit(new Task());
        }
        service.shutdown();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "调用了慢服务");
            try {
                //模拟慢服务
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
