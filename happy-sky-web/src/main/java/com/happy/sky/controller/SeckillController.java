package com.happy.sky.controller;

import com.happy.sky.config.seckill.MyThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: RedisController <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/31 9:24 <tb>
 */
@Slf4j
@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private RedisTemplate redisTemplate ;

    @GetMapping("/send")
    public void send(){
        for(int i = 0; i < 1000; i++){
            int number = 10 ;
            Runnable thread = new MyThread(number,redisTemplate);
            new Thread(thread).run();
        }
    }
}
