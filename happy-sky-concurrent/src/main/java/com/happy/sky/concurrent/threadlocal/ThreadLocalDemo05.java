package com.happy.sky.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @name: ThreadLocalDemo05 <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 10:57 <tb>
 */
public class ThreadLocalDemo05 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalDemo05().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        String s = null ;
        synchronized (ThreadLocalDemo05.class){
            s = dateFormat.format(date);
        }
        return s;
    }
}
