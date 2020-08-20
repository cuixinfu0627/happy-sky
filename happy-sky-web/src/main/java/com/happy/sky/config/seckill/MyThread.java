package com.happy.sky.config.seckill;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @name: MyThread <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/31 9:27 <tb>
 */
public class MyThread implements Runnable {

    private Integer number ;

    private RedisTemplate redisTemplate ;

    public MyThread(int number, RedisTemplate redisTemplate) {
        this.number = number ;
        this.redisTemplate = redisTemplate ;
    }

    @Override
    public void run() {
        Object obj = redisTemplate.opsForValue().get(com.happy.sky.config.seckill.WebListener.GOOD_KEY + 1);
        Integer redisStock = Integer.valueOf(obj.toString());
        if (redisStock == 0){
            System.out.println("秒杀结束=========end");
            return;
        }
        System.out.println("库存量：" + redisStock);
        long stock = redisTemplate.getConnectionFactory().getConnection()
                .decr(redisTemplate.getKeySerializer().serialize(com.happy.sky.config.seckill.WebListener.GOOD_KEY + "1"));
        if (stock < 0 ){
            redisStock = (Integer) redisTemplate.opsForValue().get(com.happy.sky.config.seckill.WebListener.GOOD_KEY + 1);
            if (redisStock !=0 && redisStock < number){
                redisTemplate.opsForValue().increment(com.happy.sky.config.seckill.WebListener.GOOD_KEY + 1,number);
                System.out.println("购买商品" + number + "大于库存数量，返回库存"+number);
            }else if (redisStock == 0){
                 redisTemplate.opsForValue().set(com.happy.sky.config.seckill.WebListener.GOOD_KEY + 1,0);
            }
            System.err.println("redis 缓存数量：" + redisTemplate.opsForValue().get(com.happy.sky.config.seckill.WebListener.GOOD_KEY + 1));
            System.err.println("库存不足,秒杀失败!");
        }
        Map<String,Object> params = new HashMap();
        params.put("id",1);
        params.put("number",number);
        redisTemplate.convertAndSend("TOPIC_USERNAME", JSON.toJSONString(params));
    }
}
