package com.happy.sky.controller;

import com.happy.sky.common.utils.MD5Util;
import com.happy.sky.common.utils.ThreadLocalUtil;
import com.happy.sky.entity.AccessToken;
import com.happy.sky.entity.AppInfo;
import com.happy.sky.entity.TokenInfo;
import com.happy.sky.entity.UserInfo;
import com.happy.sky.entity.constant.NotRepeatSubmit;
import com.happy.sky.web.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @name: TokenController <tb>
 * @title: https://mp.weixin.qq.com/s/iw70sG8_P8oS1TLJAC52-A  <tb>
 *  API 面试四连杀：接口如何设计？安全如何保证？签名如何实现？防重如何实现？
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/21 10:40 <tb>
 */
@Slf4j
@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * API Token
     * https://mp.weixin.qq.com/s/iw70sG8_P8oS1TLJAC52-A
     * token:访问令牌access token,Token的值一般是UUID
     * timestamp :时间戳，是客户端调用接口时对应的当前时间戳，时间戳用于防止DoS攻击
     * sign :
     *  nonce：随机值，是客户端随机生成的值，作为参数传递过来，随机值的目的是增加sign签名的多变性,数字和字母的组合，6位长度
     *  sign: 一般用于参数签名，防止参数被非法篡改，最常见的是修改金额等重要敏感参数，
     *  sign的值一般是将所有非空参数按照升续排序然后+token+key+timestamp+nonce(随机数)拼接在一起，然后使用某种加密算法进行加密
     * @return
     */
    @PostMapping("/api_token")
    public ApiResponse<AccessToken> apiToken(String appId, @RequestHeader("timestamp") String timestamp, @RequestHeader("sign") String sign) {
        Assert.isTrue(!StringUtils.isEmpty(appId) && !StringUtils.isEmpty(timestamp) && !StringUtils.isEmpty(sign), "参数错误");

        long reqeustInterval = System.currentTimeMillis() - Long.valueOf(timestamp);
        Assert.isTrue(reqeustInterval < 5 * 60 * 1000, "请求过期，请重新请求");

        // 1. 根据appId查询数据库获取appSecret
        AppInfo appInfo = new AppInfo("1", "12345678954556");

        // 2. 校验签名
        String signString = timestamp + appId + appInfo.getKey();
        String signature = MD5Util.encode(signString);
        log.info(signature);
        Assert.isTrue(signature.equals(sign), "签名错误");

        // 3. 如果正确生成一个token保存到redis中，如果错误返回错误信息
        AccessToken accessToken = this.saveToken(0, appInfo, null);

        return ApiResponse.success(accessToken);
    }


    @NotRepeatSubmit(5000)
    @PostMapping("user_token")
    public ApiResponse<UserInfo> userToken(String username, String password) {
        // 根据用户名查询密码, 并比较密码(密码可以RSA加密一下)
        UserInfo userInfo = new UserInfo(username, "81255cb0dca1a5f304328a70ac85dcbd", "111111");
        String pwd = password + userInfo.getSalt();
        String passwordMD5 = MD5Util.encode(pwd);
        Assert.isTrue(passwordMD5.equals(userInfo.getPassword()), "密码错误");

        // 2. 保存Token
        AppInfo appInfo = new AppInfo("1", "12345678954556");
        AccessToken accessToken = this.saveToken(1, appInfo, userInfo);
        userInfo.setAccessToken(accessToken);
        return ApiResponse.success(userInfo);
    }

    private AccessToken saveToken(int tokenType, AppInfo appInfo,  UserInfo userInfo) {
        String token = UUID.randomUUID().toString();

        // token有效期为2小时
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, 7200);
        Date expireTime = calendar.getTime();

        // 4. 保存token
        ValueOperations<String, TokenInfo> operations = redisTemplate.opsForValue();
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setTokenType(tokenType);
        tokenInfo.setAppInfo(appInfo);

        if (tokenType == 1) {
            tokenInfo.setUserInfo(userInfo);
        }

        operations.set(token, tokenInfo, 7200, TimeUnit.SECONDS);

        AccessToken accessToken = new AccessToken(token, expireTime);

        //TODO cuixinfu
        ThreadLocalUtil.set("loginUser",userInfo);
        return accessToken;
    }

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        String signString = timestamp + "1" + "12345678954556";
        String sign = MD5Util.encode(signString);
        System.out.println(sign);

        System.out.println("-------------------");
        signString = "password=123456&username=1&12345678954556" + "ff03e64b-427b-45a7-b78b-47d9e8597d3b1529815393153sdfsdfsfs" + timestamp + "A1scr6";
        sign = MD5Util.encode(signString);
        System.out.println(sign);
    }
}