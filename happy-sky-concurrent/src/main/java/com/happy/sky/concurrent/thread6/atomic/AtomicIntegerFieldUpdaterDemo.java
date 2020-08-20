package com.happy.sky.concurrent.thread6.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @name: AtomicIntegerFieldUpdaterDemo <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 10:20 <tb>
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    static Score math;
    static Score computer;

    public static AtomicIntegerFieldUpdater<Score> scoreUpdater = AtomicIntegerFieldUpdater
            .newUpdater(Score.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            computer.score++;
            scoreUpdater.getAndIncrement(math);
        }
    }

    public static class Score {
        volatile int score;
    }

    public static void main(String[] args) throws InterruptedException {
        math = new Score();
        computer = new Score();
        AtomicIntegerFieldUpdaterDemo2 r = new AtomicIntegerFieldUpdaterDemo2();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("普通变量的结果：" + computer.score);
        System.out.println("升级后的结果：" + math.score);
    }
}
