package com.happy.sky.concurrent.producer.consumer;

import java.util.LinkedList;

/**
 * @name: MyBlockingQueue <tb>
 * @title: wait/notify 实现生产者消费者模式  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/11 16:08 <tb>
 */
class MyBlockingQueue {

    private int maxSize;
    private LinkedList<Object> storage;

    public MyBlockingQueue(int size) {
        this.maxSize = size;
        storage = new LinkedList<>();
    }

    public synchronized void put() throws InterruptedException {
        while (storage.size() == maxSize) {
            wait();
        }
        storage.add(new Object());
        notifyAll();
    }

    public synchronized void take() throws InterruptedException {
        while (storage.size() == 0) {
            wait();
        }
        System.out.println(storage.remove());
        notifyAll();
    }
}

