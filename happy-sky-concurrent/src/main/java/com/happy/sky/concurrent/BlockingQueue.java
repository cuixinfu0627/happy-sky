package com.happy.sky.concurrent;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @name: BlockingQueue <tb>
 * @title: 线程唤醒  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/11 15:32 <tb>
 */
public class BlockingQueue {
    Queue<String> buffer = new LinkedList<String>();

    public void give (String data){
        synchronized (this){
            buffer.add(data);
            notify(); // Sincesomeonemaybewaitingintake
        }
    }

    public String take()throws InterruptedException{
        synchronized(this){
            while(buffer.isEmpty()){
                wait();
            }
            return buffer.remove();
        }
    }
}

