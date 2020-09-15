package com.happy.sky.web.util;


import com.happy.sky.common.utils.RequestHeaderUtil;
import com.happy.sky.entity.constant.OperationType;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @name: AppContextUtil <tb>
 * @title: ThreadLocal是线程内的全局上下文。就是在单个线程中，方法之间共享的内存，每个方法都可以从该上下文中获取值和修改值。<tb>
 * 在调用api时都会传一个token参数，通常会写一个拦截器来校验token是否合法，我们可以通过token找到对应的用户信息(User)，
 * 如果token合法，然后将用户信息存储到ThreadLocal中，这样无论是在controller、service、dao的哪一层都能访问到该用户的信息。作用类似于Web中的request作用域。
 * <p>
 * 传统方式我们要在方法中访问某个变量，可以通过传参的形式往方法中传参，如果多个方法都要使用那么每个方法都要传参；
 * 如果使用ThreadLocal所有方法就不需要传该参数了，每个方法都可以通过ThreadLocal来访问该值。
 * ThreadLocalUtil.set("key", value); 保存值
 * T value = ThreadLocalUtil.get("key"); 获取值
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/21 10:52 <tb>
 */
public class AppContextUtil {

    private static final ThreadLocal requestThreadUser = new ThreadLocal<>();

    private static final ThreadLocal requestThreadUserRole = new ThreadLocal<>();

    private static final ThreadLocal<String> requestThreadRequestIp = new ThreadLocal<>();

    private static final ThreadLocal<String> requestThreadModule = new ThreadLocal<>();

    private static final ThreadLocal<OperationType.Function> requestThreadFunction = new ThreadLocal<>();

    private static final Map<Long, Map<Boolean, String>> userIdSessionMapping = new ConcurrentHashMap<>();

    /**
     * 设置当前线程User
     */
    public static <T> void setCurrentUser(T user) {
        if (user == null) {
            return;
        }
        requestThreadUser.set(user);
    }

    /**
     * 设置当前线程用户角色
     */
    public static <T> void setCurrentUserRole(T role) {
        if (role == null) {
            return;
        }
        requestThreadUserRole.set(role);
    }

    /**
     * 设置当前线程User
     */
    public static void setRequestIp(String ip) {
        if (ip == null) {
            return;
        }
        requestThreadRequestIp.set(ip);
    }

    /**
     * 设置当前线程User
     */
    public static String getRequestIp() {
        return requestThreadRequestIp.get();
    }

    /**
     * 设置当前线程User
     */
    public static void setFunction(OperationType.Function function) {
        if (function == null) {
            return;
        }
        requestThreadFunction.set(function);
    }

    /**
     * 获取当前线程User
     */
    public static <T> T getCurrentUser() {
        return (T) requestThreadUser.get();
    }

    /**
     * 获取当前线程用户角色
     */
    public static <T> T getCurrentUserRole() {
        return (T) requestThreadUserRole.get();
    }

    /**
     * 设置当前线程User
     */
    public static String getModule() {
        return requestThreadModule.get();
    }

    /**
     * 设置当前线程User
     */
    public static OperationType.Function getFunction() {
        return requestThreadFunction.get();
    }

    /**
     * 去除当前线程User
     */
    public static void removeCurrentUser() {
        requestThreadUser.remove();
    }


    /**
     * 去除当前线程User
     */
    public static void removeRequestIp() {
        requestThreadRequestIp.remove();
    }

    /**
     * 去除当前线程User
     */
    public static void removeRequestFunction() {
        requestThreadFunction.remove();
    }

    /**
     * 去除当前线程User
     */
    public static void removeRequestModule() {
        requestThreadModule.remove();
    }


    public static void addUserSessionId(long userId, String sessionId, String userAgent) {
        Map<Boolean, String> map = userIdSessionMapping.computeIfAbsent(userId, (Long key) -> new ConcurrentHashMap<Boolean, String>());
        map.put(RequestHeaderUtil.isMobileDevice(userAgent), sessionId);
    }

    public static void removeUserSession(long userId, String sessionId) {
        Map<Boolean, String> userSessions = userIdSessionMapping.get(userId);
        if (userSessions != null && !userSessions.isEmpty()) {
            for (Map.Entry<Boolean, String> entry : userSessions.entrySet()) {
                Boolean key = entry.getKey();
                if (entry.getValue().equalsIgnoreCase(sessionId)) {
                    userSessions.remove(key);
                }
            }
        }
    }

    public static Map<Boolean, String> getUserSessions(long userId) {
        return userIdSessionMapping.getOrDefault(userId, Collections.emptyMap());
    }

}
