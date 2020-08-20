package com.happy.sky.config.seckill;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @name: WebListener <tb>
 * @title: 下面直接上代码系统初始化的时候将秒杀商品库存放入redis缓存  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/31 9:03 <tb>
 */
@Component
public class WebListener implements InitializingBean {

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Autowired
    private RedisTemplate redisTemplate ;

    public static final String GOOD_KEY = "goods:";

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> goods = jdbcTemplate.queryForMap("select * from goods_info where id = ?", 1);
        String goodsId = goods.get("id").toString();
        String goodsStock = goods.get("stock").toString();
        redisTemplate.opsForValue().set(GOOD_KEY + goodsId,goodsStock);
    }
}
