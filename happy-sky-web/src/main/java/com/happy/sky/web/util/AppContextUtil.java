package com.happy.sky.web.util;


import com.happy.sky.common.utils.RequestHeaderUtil;
import com.happy.sky.entity.constant.OperationType;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @name: AppContextUtil <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/21 11:02 <tb>
 */
public class AppContextUtil {

    private static final ThreadLocal requestThreadUser = new ThreadLocal<>() ;

    private static final ThreadLocal requestThreadUserRole = new ThreadLocal<>() ;

    private static final ThreadLocal<String> requestThreadRequestIp = new ThreadLocal<>() ;

    private static final ThreadLocal<String> requestThreadModule = new ThreadLocal<>() ;

    private static final ThreadLocal<OperationType.Function> requestThreadFunction = new ThreadLocal<>() ;

    private static final Map<Long, Map<Boolean,String>> userIdSessionMapping = new ConcurrentHashMap<>() ;

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
        return (T)requestThreadUser.get();
    }

    /**
     * 获取当前线程用户角色
     */
    public static <T> T getCurrentUserRole() {
        return (T)requestThreadUserRole.get();
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



    public static void addUserSessionId(long userId, String sessionId,String userAgent) {
        Map<Boolean,String> map = userIdSessionMapping.computeIfAbsent(userId, (Long key) ->  new ConcurrentHashMap<Boolean,String>());
        map.put(RequestHeaderUtil.isMobileDevice(userAgent),sessionId);
    }
    public static void removeUserSession(long userId, String sessionId) {
        Map<Boolean,String> userSessions = userIdSessionMapping.get(userId);
        if(userSessions != null && !userSessions.isEmpty()) {
            for (Map.Entry<Boolean, String> entry : userSessions.entrySet()) {
                Boolean key = entry.getKey();
                if (entry.getValue().equalsIgnoreCase(sessionId)) {
                    userSessions.remove(key);
                }
            }
        }
    }

    public static Map<Boolean,String> getUserSessions(long userId) {
        return userIdSessionMapping.getOrDefault(userId, Collections.emptyMap());
    }

}
