package com.happy.sky.concurrent.thread8.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @name: SemaphoreDemo2 <tb>
 * @title: 如果想要限制并发访问的线程数，用信号量是更合适的。
         * 信号量有点类似于限流手段中的令牌桶算法
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 16:50 <tb>
 */
public class SemaphoreDemo2 {

    static Semaphore semaphore = new Semaphore(3);

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
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了许可证，花费2秒执行慢服务");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("慢服务执行完毕，" + Thread.currentThread().getName() + "释放了许可证");
            semaphore.release();
        }
    }
}
