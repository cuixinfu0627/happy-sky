package com.happy.sky.redis;

import redis.clients.jedis.JedisPool;

/**
 * @name: TestPublisher <tb>
 * @title: 发布者 测试  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/9/15 9:55 <tb>
 */
public class TestPublisher {
    public static void main(String[] args) throws InterruptedException {
        JedisPool jedisPool = new JedisPool("127.0.0.1");
        // 向ldc频道发布消息
        Publisher publisher = new Publisher(jedisPool, "ldc");
        publisher.start();
    }
}
