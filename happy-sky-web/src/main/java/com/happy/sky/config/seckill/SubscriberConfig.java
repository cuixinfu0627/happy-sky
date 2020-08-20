package com.happy.sky.config.seckill;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @name: SUbscriberConfig <tb>
 * @title: 第二创建消息队列（这里为了方便，我直接使用redis队列来进行模拟操作）  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/31 9:13 <tb>
 */
@Configuration
@AutoConfigureAfter({Receiver.class})
public class SubscriberConfig {

    @Bean
    public MessageListenerAdapter getMessageListenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver);
    }

    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
                                                                          MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, new PatternTopic("TOPIC_USERNAME"));
        return redisMessageListenerContainer;
    }
}
