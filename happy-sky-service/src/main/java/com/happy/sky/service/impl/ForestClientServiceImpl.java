package com.happy.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.happy.sky.forest.MyForestClient;
import com.happy.sky.service.ForestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @name: ForestClientServiceImpl <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/17 14:27 <tb>
 */
@Service("forestClientService")
public class ForestClientServiceImpl implements ForestClientService{

    @Autowired
    private MyForestClient myForestClient;

    @Override
    public Map getLocation() throws Exception {
        Map result = myForestClient.getLocation("124.730329","31.463683");
        System.out.println(JSON.toJSONString(result,true));
        return result;
    }
}
