package com.happy.sky.config.seckill;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @name: BeanConfig <tb>
 * @title: 第三 配置RedisTemplate序列化  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/31 9:19 <tb>
 */
@Configuration
public class BeanConfig {
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        FastJsonRedisSerializer redisSerializer = new FastJsonRedisSerializer(Object.class);
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate ;
    }
}
