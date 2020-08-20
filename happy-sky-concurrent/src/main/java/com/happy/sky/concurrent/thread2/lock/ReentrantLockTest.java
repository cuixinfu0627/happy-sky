package com.happy.sky.concurrent.thread2.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @name: ReentrantLockTest <tb>
 * @title: 可重入锁  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 9:58 <tb>
 */

public class ReentrantLockTest {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> test(), "线程A ").start();
        new Thread(() -> test(), "线程B").start();

    }

    public static void test() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取了锁");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }
}