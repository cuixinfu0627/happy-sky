package com.happy.sky.config.seckill;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @name: Receiver <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/31 9:51 <tb>
 */
@Component
public class Receiver implements MessageListener {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String content = message.toString();
        Map<String,Object> params = JSONObject.parseObject(content);
        Integer number = (Integer) params.get("number");
        String updateSql = "update goods_info set stock = stock - " + number +",version = version + 1 where id = " + params.get("id") + " and stock > 0" ;
        int result = jdbcTemplate.update(updateSql);
        System.out.println("update goods_info set stock = stock - " + number +",version = version + 1 where id = " + params.get("id") + "and stock > 0");
        if (result > 0){
            System.out.println("Revicer 秒杀成功： " + number);
            // todo 执行业务订单创建

        }
    }
}
