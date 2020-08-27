package com.happy.sky.config.redis.pool;


import io.lettuce.core.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * @name: JedisPoolConnection <tb>
 * @title: 配置jedispool  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/26 11:34 <tb>
 */
@Component
public class JedisPoolConnection {

    private JedisPool jedisPool = null;

    @Autowired
    private JedisConfigPropertys parameters;

    //初始化redisWrapper (PostConstruct注解相当于静态代码库,方法会在类初始化的时候 进行执行)
    @PostConstruct
    public void init() throws RedisException {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(parameters.getMaxIdle());
            config.setMaxTotal(parameters.getMaxActive());
            config.setMaxWaitMillis(parameters.getMaxWaitMillis());

            jedisPool = new JedisPool(config, parameters.getHost(), parameters.getPort(), 2000);
        } catch (Exception e) {
            throw new RedisException("初始化redisPool失败");   //抛出异常
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

}
