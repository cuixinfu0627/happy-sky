package com.happy.sky.concurrent.thread4.blocking.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @name: BlockingQueueDemo <tb>
 * @title: 阻塞队列  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 9:30 <tb>
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        System.out.println("hello blocking queue!!!");
        //addTest();
        //removeTest();
        //offerTest();

        //pollTest();
        peekTest();
    }

    private static void addTest() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.add(1);
        blockingQueue.add(1);
        blockingQueue.add(1);
    }

    private static void removeTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        blockingQueue.add(1);
        blockingQueue.add(1);
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }

    private static void offerTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(1));
    }

    private static void pollTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(3);
        blockingQueue.offer(1);
        blockingQueue.offer(2);
        blockingQueue.offer(3);
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void peekTest() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(2);
        System.out.println(blockingQueue.peek());
    }

}
