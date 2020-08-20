package com.happy.sky.concurrent.thread.safety;

/**
 * @name: ThreadNotSafe1 <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/13 10:27 <tb>
 */
/**
 * 描述：     共享的变量或资源带来的线程安全问题
 */
public class ThreadNotSafe1 {

    static int i;

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10000; j++) {
                    i++;
                }
            }
        };
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
