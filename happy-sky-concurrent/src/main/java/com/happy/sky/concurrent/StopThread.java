package com.happy.sky.concurrent;

/**
 * @name: StopThread <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/11 14:50 <tb>
 */
public class StopThread implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 1000) {
            System.out.println("count = " + count++);
        }
    }


    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            thread.sleep(5);
        } catch (InterruptedException e) {
            //处理异常
            thread.currentThread().interrupt();
            e.printStackTrace();
        }
        thread.interrupt();
    }


}
