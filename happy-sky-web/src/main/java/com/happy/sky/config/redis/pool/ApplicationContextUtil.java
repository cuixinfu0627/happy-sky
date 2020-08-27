package com.happy.sky.config.redis.pool;

/**
 * @name: ApplicationContextUtil <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/26 11:37 <tb>
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring ApplicationContext工具类. <br>
 * 获取web应用的applicationContext及其管理的bean.
 *
 * @version 1.0
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    /**
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ApplicationContextUtil.applicationContext == null) {
            ApplicationContextUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext.
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取applicationContext中的bean.
     *
     * @param name         beanId
     * @param requiredType bean类型
     * @return bean
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return getApplicationContext().getBean(name, requiredType);
    }
}

