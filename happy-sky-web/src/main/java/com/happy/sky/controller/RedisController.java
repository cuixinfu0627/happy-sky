package com.happy.sky.controller;

import com.happy.sky.entity.User;
import com.happy.sky.redis.Publisher;
import com.happy.sky.redis.SubThread;
import com.happy.sky.redis.Subscriber;
import com.happy.sky.view.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public R welcome(@RequestParam(value = "userId", defaultValue = "1") String userId,
                     HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setUserId(Long.valueOf(userId));
        user.setUsername("redis");
        user.setNickname("redis hello-world!");
        user.setMobile("15010089116+");
        return R.ok().put("user", user);
    }

    /**
     * redis 发布者 测试
     * @param request
     * @param response
     */
    @RequestMapping(value = "/publisher", method = RequestMethod.GET)
    public R publisher(HttpServletRequest request, HttpServletResponse response) {
        logger.info("redis publisher message:{}", new Date());
        JedisPool jedisPool = new JedisPool("127.0.0.1");
        // 向ldc频道发布消息
        Publisher publisher = new Publisher(jedisPool, "ldc");
        publisher.start();
        return R.ok();
    }

    /**
     * redis 订阅者 测试
     * @param request
     * @param response
     */
    @RequestMapping(value = "/subscriber", method = RequestMethod.GET)
    public R subscriber(HttpServletRequest request, HttpServletResponse response) {
        logger.info("redis subscriber message:{}",new Date());
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        Subscriber subscriber = new Subscriber("黎杜");
        // 订阅ldc频道
        SubThread thread = new SubThread(jedisPool, subscriber, "ldc");
        thread.start();
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 取消订阅
        subscriber.unsubscribe("ldc");
        return R.ok();
    }

}
