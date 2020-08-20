package com.happy.sky.concurrent.thread1.pool;

/**
 * @name: OneTask <tb>
 * @title: 单个任务的时候，新建线程来执行  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/13 11:04 <tb>
 */
public class OneTask {

    public static void main(String[] args) {
        Thread thread0 = new Thread(new Task());
        thread0.start();
    }

    static class Task implements Runnable {

        public void run() {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }
}

