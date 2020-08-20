package com.happy.sky.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @name: VolatileCanStop <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/11 15:06 <tb>
 */
public class VolatileCanStop {


    static class Producer implements Runnable {
        public volatile boolean canceled = true;
        BlockingQueue storage;

        public Producer(BlockingQueue storage){
            this.storage=storage;
        }

        @Override
        public void run(){
            int num=0;
            try{
                while(num <= 100000 && canceled){
                    if(num % 50 == 0){
                        storage.put(num);
                        System.out.println(num+"是50的倍数,被放到仓库中了。");
                    }
                    num++;
                }
            }catch(InterruptedException e){
                System.out.println("producerThread.interrupt(): 停止时异常："+e.getMessage());
                e.printStackTrace();
            }finally{
                System.out.println("生产者结束运行");
            }
        }
    }

    static class Consumer{
        BlockingQueue storage;
        public Consumer(BlockingQueue storage){
            this.storage=storage;
        }
        public boolean needMoreNums(){
            if(Math.random()>0.97){
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue storage = new ArrayBlockingQueue(8);
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(500);

        Consumer consumer= new Consumer(storage);
        while(consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        //一旦消费不需要更多数据了，我们应该让生产者也停下来，但是实际情况却停不下来
        producer.canceled=false;
        producerThread.interrupt(); //使用interrupt() 来停止
        System.out.println(producer.canceled);
    }

}
