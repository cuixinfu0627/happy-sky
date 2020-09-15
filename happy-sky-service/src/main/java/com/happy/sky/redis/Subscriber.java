package com.happy.sky.redis;

import redis.clients.jedis.JedisPubSub;

/**
 * 订阅者
 * @author liduchang
 */
public class Subscriber extends JedisPubSub {
    //订阅频道名称
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    /**
     * 订阅者收到消息时会调用
     */
    @Override
    public void onMessage(String channel, String message) {
        // TODO Auto-generated method stub
        super.onMessage(channel, message);
        System.out.println("频道："+channel+"  接受的消息为："+message);
    }

    /**
     * 订阅了频道会被调用
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("订阅了频道:"+channel+"  订阅数为："+subscribedChannels);
    }

    /**
     * 取消订阅频道会被调用
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("取消订阅的频道："+channel+"  订阅的频道数量为："+subscribedChannels);
    }
}