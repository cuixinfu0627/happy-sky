package com.happy.sky.concurrent.producer.consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @name: BlockingQueueTest <tb>
 * @title:  BlockingQueue 实现生产者消费者模式  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/11 16:03 <tb>
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);

        Runnable producer=()->{
            while(true){
                try {
                    queue.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(producer).start();
        new Thread(producer).start();

        Runnable consumer=()->{
            while(true){
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
