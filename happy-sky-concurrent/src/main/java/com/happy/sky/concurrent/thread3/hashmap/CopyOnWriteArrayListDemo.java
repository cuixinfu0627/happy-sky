package com.happy.sky.concurrent.thread3.hashmap;

/**
 * @name: CopyOnWriteArrayListDemo <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/17 11:40 <tb>
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {

        HelloThread ht = new HelloThread();

        for (int i = 0; i < 10 ; i++) {
            new Thread(ht).start();
        }
    }

    static class HelloThread implements Runnable {


        private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        static {
            list.add("AA");
            list.add("BB");
            list.add("CC");
        }

        @Override
        public void run() {

            Iterator<String> it = list.iterator();

            while(it.hasNext()) {
                System.out.println(it.next());

                list.add("AA");
            }
        }
    }
}