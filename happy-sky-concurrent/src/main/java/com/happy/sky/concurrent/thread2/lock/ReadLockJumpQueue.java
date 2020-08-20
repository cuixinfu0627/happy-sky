package com.happy.sky.concurrent.thread2.lock;



import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @name: ReadLockJumpQueue <tb>
 * @title: 演示读锁不插队  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/14 9:54 <tb>
 */
public class ReadLockJumpQueue {

    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock
            .readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock
            .writeLock();

    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> read(),"Thread-2").start();
        new Thread(() -> read(),"Thread-4").start();
        new Thread(() -> write(),"Thread-3").start();
        new Thread(() -> read(),"Thread-5").start();
    }
}
