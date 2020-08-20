package com.happy.sky.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @name: ThreadLocalStatic <tb>
 * @title: ThreadLocal 并不是用来解决共享资源问题的。
 * 虽然 ThreadLocal 确实可以用于解决多线程情况下的线程安全问题，但其资源并不是共享的，
 * 而是每个线程独享的。所以这道题其实是有一定陷阱成分在内的。  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 11:42 <tb>
 */
public class ThreadLocalStatic {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);

    static SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalStatic().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = ThreadSafeFormatter2.dateFormatThreadLocal.get();
        return dateFormat.format(date);
    }
}

class ThreadSafeFormatter2 {

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return ThreadLocalStatic.dateFormat;
        }
    };
}
