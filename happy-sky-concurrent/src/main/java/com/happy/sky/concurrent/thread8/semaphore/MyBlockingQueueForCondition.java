package com.happy.sky.concurrent.thread8.semaphore;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @name: MyBlockingQueueForCondition <tb>
 * @title: 用 Condition 实现简易版阻塞队列  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 17:20 <tb>
 */
public class MyBlockingQueueForCondition {

    private Queue queue;
    private int max = 16;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public MyBlockingQueueForCondition(int size) {
        this.max = size;
        queue = new LinkedList();
    }

    public void put(Object o) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == max) {
                notFull.await();
            }
            queue.add(o);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            Object item = queue.remove();
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @name: MyBlockingQueueForCondition <tb>
 * @title: 用 wait/notify 实现简易版阻塞队列  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 17:21<tb>
 */
class MyBlockingQueueForWaitNotify {

    private int maxSize;
    private LinkedList<Object> storage;

    public MyBlockingQueueForWaitNotify (int size) {
        this.maxSize = size;
        storage = new LinkedList<>();
    }

    public synchronized void put() throws InterruptedException {
        while (storage.size() == maxSize) {
            this.wait();
        }
        storage.add(new Object());
        this.notifyAll();
    }

    public synchronized void take() throws InterruptedException {
        while (storage.size() == 0) {
            this.wait();
        }
        System.out.println(storage.remove());
        this.notifyAll();
    }
}
