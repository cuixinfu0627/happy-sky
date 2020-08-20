package com.happy.sky.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @name: FileUploadUtil <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/8 14:28 <tb>
 */
public class FileUploadUtil {
    /**
     * 根据文件名获取文件扩展名
     * @param name
     * @return
     */
    public static String getFileExpandName(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        return name.substring(name.lastIndexOf(".") + 1, name.length());
    }

    /**
     * 根据文件名获取文件前缀
     * @param name
     * @return
     */
    public static String getFilePrefix(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        return name.substring(0,name.lastIndexOf("."));
    }
}
