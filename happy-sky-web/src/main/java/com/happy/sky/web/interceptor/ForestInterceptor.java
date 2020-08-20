package com.happy.sky.web.interceptor;

import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @name: ForestInterceptor <tb>
 * @title: Forest拦截器  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/20 11:11 <tb>
 */
public class ForestInterceptor implements Interceptor<String> {

    private final static Logger log = LoggerFactory.getLogger(ForestInterceptor.class);

    /**
     * 该方法在请求发送之前被调用, 若返回false则不会继续发送请求
     */
    @Override
    public boolean beforeExecute(ForestRequest request) {
        log.info("invoke Simple beforeExecute");
        // 执行在发送请求之前处理的代码
        request.addHeader("accessToken", "11111111");  // 添加Header
        request.addQuery("username", "foo");  // 添加URL的Query参数
        return true;  // 继续执行请求返回true
    }

    /**
     * 该方法在请求成功响应时被调用
     */
    @Override
    public void onSuccess(String data, ForestRequest request, ForestResponse response) {
        log.info("invoke Simple onSuccess");
        // 执行成功接收响应后处理的代码
        int status = response.getStatusCode(); // 获取请求响应状态码
        String content = response.getContent(); // 获取请求的响应内容
        String result = data;  // data参数是方法返回类型对应的返回数据结果
        result = (String) response.getResult(); // getResult()也可以获取返回的数据结果
        response.setResult("修改后的结果: " + result);  // 可以修改请求响应的返回数据结果
    }

    /**
     * 该方法在请求发送失败时被调用
     */
    @Override
    public void onError(ForestRuntimeException ex, ForestRequest request, ForestResponse response) {
        log.info("invoke Simple onError");
        // 执行发送请求失败后处理的代码
        int status = response.getStatusCode(); // 获取请求响应状态码
        String content = response.getContent(); // 获取请求的响应内容
        String result = (String) response.getResult(); // 获取方法返回类型对应的返回数据结果
    }

    /**
     * 该方法在请求发送之后被调用
     */
    @Override
    public void afterExecute(ForestRequest request, ForestResponse response) {
        log.info("invoke Simple afterExecute");
        // 执行在发送请求之后处理的代码
        int status = response.getStatusCode(); // 获取请求响应状态码
        String content = response.getContent(); // 获取请求的响应内容
        String result = (String) response.getResult(); // 获取方法返回类型对应的最终数据结果
    }
}
