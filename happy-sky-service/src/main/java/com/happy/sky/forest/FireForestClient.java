package com.happy.sky.forest;

import com.dtflys.forest.annotation.DataObject;
import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Request;

import java.util.Map;

/**
 * @name: ForestClientService <tb>
 * @title: https://dt_flys.gitee.io/forest  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/17 14:26 <tb>
 */
public interface FireForestClient {

    /**
     * @title: 获取单位-建筑-楼层-单元-房间列表 <tb>
     * @params: base  url地址<tb>
     * @params: sign  签名<tb>
     * @params: data  body<tb>
     */
    @Request(
            url = "${base}?sign=${sign}",
            type = "post",
            contentType = "application/json",
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: application/json"
            },
            dataType = "json",
            data = "${data}"
    )
    Map queryFireList(
            @DataVariable("base") String base,
            @DataVariable("sign") String sign,
            @DataParam("data") String data
    );

    /**
     * @title: 新增或修改设备 <tb>
     * @params: base  url地址<tb>
     * @params: sign  签名<tb>
     * @params: data  body<tb>
     */
    @Request(
            url = "${base}?sign=${sign}",
            type = "post",
            dataType = "json",
            contentType = "application/json",
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: application/json"
            }
    )
    Map savaOrUpdateDevice(
            @DataVariable("base") String base,
            @DataVariable("sign") String sign,
            @DataObject FireDeviceParam fireDeviceParam
    );

    /**
     * @title:  删除设备 <tb>
     * @params: base  url地址<tb>
     * @params: sign  签名<tb>
     * @params: data  body<tb>
     */
    @Request(
            url = "${base}?sign=${sign}",
            type = "delete",
            contentType = "application/json",
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: application/json"
            },
            dataType = "json",
            data = "${data}"
    )
    Map deleteDevice(
            @DataVariable("base") String base,
            @DataVariable("sign") String sign,
            @DataParam("data") String data
    );

}
