package com.happy.sky.redis;

import redis.clients.jedis.JedisPool;

/**
 * @name: TestSubscriber1 <tb>
 * @title: 订阅者 测试  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/9/15 9:56 <tb>
 */
public class TestSubscriber1 {
    public static void main(String[] args) throws InterruptedException {
        JedisPool jedisPool = new JedisPool("127.0.0.1",6379);
        Subscriber subscriber = new Subscriber("黎杜");
        // 订阅ldc频道
        SubThread thread= new SubThread(jedisPool, subscriber, "ldc");
        thread.start();
        Thread.sleep(600000);
        // 取消订阅
        subscriber.unsubscribe("ldc");
    }
}
