package com.happy.sky.controller;

import com.alibaba.fastjson.JSON;
import com.dtflys.forest.config.ForestConfiguration;
import com.happy.sky.forest.FireForestClient;
import com.happy.sky.service.ForestClientService;
import com.happy.sky.view.R;
import com.happy.sky.view.ResultVoWrapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @name: ForestClientController <tb>
 * @title: https://gitee.com/dt_flys/forest  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/17 14:30 <tb>
 */
@RestController
@RequestMapping("/forest")
public class ForestClientController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ForestClientService forestClientService;

    @Resource(name = "config0")
    private ForestConfiguration config0;

    @Autowired
    private FireForestClient fireForestClient;

    @GetMapping("/getLocation")
    public R getLocation(@RequestParam(value = "cityName") String cityName) throws Exception {
        System.out.println("request receive params cityName: " + cityName);
        Map location = forestClientService.getLocation();
        return R.ok().put("data", location);
    }

    /** ########################### 获取消防平台单位下相关基础信息接口 ############################"**/
    /**
     * @title: 单位列表 <tb>
     * @params: projectId  <tb
     */
    @GetMapping(value = "hello")
    public Object hello() {
        String appSecret = "132456789";
        String url = "http://127.0.0.1:8080:/forest/list";
        Map<String,Object> data = new HashMap<>();
        data.put("time",System.currentTimeMillis());
        String body = JSON.toJSONString(data);
        String sign = DigestUtils.md5DigestAsHex(("POST" + url + body + appSecret).getBytes());
        return fireForestClient.queryFireList(url, sign, body);
    }

    /**
     * @title: 请输入描述 <tb>
     * @params: 测试返回数据  <tb
     */
    @PostMapping(value = "list")
    public Object list(HttpServletRequest request,
                         @RequestParam("sign") String sign,
                         @RequestBody String data) {

        //获取配置信息
        Map<String, Object> variables = config0.getVariables();
        String forestVariables = JSON.toJSONString(variables);
        logger.info("get forest config info :{}",forestVariables);

        String appSecret = "9c1fe55af3b3444991a51f23d6775693";
        String url = request.getRequestURL().toString();

        String signature = DigestUtils.md5DigestAsHex(("POST" + url + data + appSecret).getBytes());
        logger.info("upload platform get the md5 signature result :{}",signature);
        if (!signature.equals(sign)){
            return ResultVoWrapper.buildFail();
        }
        List<Object> unitList = new ArrayList<>();
        Unit  unit1 = new Unit(1L, "单位一");
        Unit  unit2 = new Unit(2L, "单位二");
        Unit  unit3 = new Unit(3L, "单位三");
        unitList.add(unit1);
        unitList.add(unit2);
        unitList.add(unit3);
        return ResultVoWrapper.buildSuccess(unitList);
    }
    @Data
    class Unit{
        private Long id;
        private String name;

        public Unit(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
