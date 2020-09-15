package com.happy.sky.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 发布者
 * @author liduchang
 *
 */
public class Publisher extends Thread{
    // 连接池
    private final JedisPool jedisPool;
    // 发布频道名称
    private String name;

    public Publisher(JedisPool jedisPool, String name) {
        super();
        this.jedisPool = jedisPool;
        this.name = name;
    }

    @Override
    public void run() {
        // 获取要发布的消息
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // 获取连接
        Jedis resource = jedisPool.getResource();
        while (true) {
            String message = null;
            try {
                message = reader.readLine();
                if (!"exit".equals(message)) {
                    // 发布消息
                    resource.publish(name, "发布者:"+Thread.currentThread().getName()+"发布消息："+message);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}