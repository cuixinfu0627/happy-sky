package com.happy.sky.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 订阅者线程
 *
 * @author liduchang
 */
public class SubThread extends Thread {

    private final JedisPool jedisPool;

    private final Subscriber subscriber;

    private String name;

    public SubThread(JedisPool jedisPool, Subscriber subscriber, String name) {
        super();
        this.jedisPool = jedisPool;
        this.subscriber = subscriber;
        this.name = name;
    }

    @Override
    public void run() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 订阅频道为name
            jedis.subscribe(subscriber, name);
        } catch (Exception e) {
            System.err.println("订阅失败");
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
                //归还连接到redis池中
                //jedisPool.returnResource(jedis);
                jedisPool.close();
            }
        }
    }
}