package com.happy.sky.concurrent.thread1.pool;

/**
 * @name: TenTask <tb>
 * @title: 线程数 = CPU 核心数 *（1+平均等待时间/平均工作时间）
 * 结论     
 * 综上所述我们就可以得出一个结论：
 *
 * 线程的平均工作时间所占比例越高，就需要越少的线程；
 *
 * 线程的平均等待时间所占比例越高，就需要越多的线程；
 *
 * 针对不同的程序，进行对应的实际测试就可以得到最合适的选择。<tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/13 11:17 <tb>
 */
public class TenTask {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }

    static class Task implements Runnable {

        public void run() {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }
}
