package com.happy.sky.config.token;

import com.happy.sky.web.interceptor.AccessLimitInterceptor;
import com.happy.sky.web.interceptor.ApiIdempotentInterceptor;
import com.happy.sky.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @name: WebConfigInterceptor <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/13 15:41 <tb>
 */
@Configuration
public class WebInterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 接口幂等性拦截器
        registry.addInterceptor(apiIdempotentInterceptor());
        // 接口防刷限流拦截器
        registry.addInterceptor(accessLimitInterceptor());
        // 登录校验拦截器
        registry.addInterceptor(loginInterceptor());

        super.addInterceptors(registry);
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public ApiIdempotentInterceptor apiIdempotentInterceptor() {
        return new ApiIdempotentInterceptor();
    }

    @Bean
    public AccessLimitInterceptor accessLimitInterceptor() {
        return new AccessLimitInterceptor();
    }

}
