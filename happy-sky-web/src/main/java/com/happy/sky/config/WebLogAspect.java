package com.happy.sky.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @name: WebLogAspect <tb>
 * @title: Apo拦截添加接口日志信息  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/3/26 16:39<tb>
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    //两个..代表所有子目录，最后括号里的两个..代表所有参数
    @Pointcut("execution(public * com.happy.sky.controller..*.*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("请求地址 : " + request.getRequestURL().toString());
        //logger.info("HTTP METHOD : " + request.getMethod());
        //logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."  + joinPoint.getSignature().getName());
        //logger.info("参数[对象] : " + JSONUtil.getJsonStr(Arrays.toString(joinPoint.getArgs())));
        StringBuilder sb = new StringBuilder(1000);
        sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
        logger.info("参数 : " + sb.toString());
    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) throws Throwable {
        //logger.info("返回值 : " + JSONUtil.getJsonStr(ret));
        //logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        //logger.info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }

    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String[]> e:map.entrySet()){
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if(value != null && value.length == 1){
                sb.append(value[0]).append("\t");
            }else{
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }
}
