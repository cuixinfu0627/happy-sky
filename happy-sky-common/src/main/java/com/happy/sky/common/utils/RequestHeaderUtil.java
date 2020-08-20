package com.happy.sky.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @name: RequestHeaderUtil <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/21 11:03 <tb>
 */
public class RequestHeaderUtil {
    public static final String[] deviceArray = new String[]{"android", "ios", "windows phone"};

    public RequestHeaderUtil() {
    }

    public static boolean isMobileDevice(String requestHeader) {
        if (requestHeader == null) {
            return false;
        } else {
            requestHeader = requestHeader.toLowerCase();

            for(int i = 0; i < deviceArray.length; ++i) {
                if (requestHeader.indexOf(deviceArray[i]) >= 0) {
                    return true;
                }
            }

            return false;
        }
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
