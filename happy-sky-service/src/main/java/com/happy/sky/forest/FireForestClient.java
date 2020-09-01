package com.happy.sky.forest;

import com.dtflys.forest.annotation.DataObject;
import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Request;
import com.happy.sky.forest.request.params.FireDevice;
import com.happy.sky.forest.request.params.FireInspection;

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
            @DataObject FireDevice fireDevice
    );

    /**
     * @title: 删除设备 <tb>
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


    @Request(
            url = "${base}",
            type = "get",
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: application/json",
                    "Cookie: ticket=0e52e10b7841430fb6d085832ce0f69a"
            }
    )
    Object queryUnitList(
            @DataVariable("base") String base
    );


    /**
     * =========================== ===========================消防平台添加建筑=========================== ===========================
     **/
    @Request(
            url = "${base}/building/addBuilding?name=${name}&unitId=${unitId}&unitName=${unitName}&location=${location}&area=${area}" +
                    "&heightOfBuilding=${heightOfBuilding}&floors=${floors}&structure=${structure}&buildYear=${buildYear}&property=${property}" +
                    "&linkname=${linkname}&phone=${phone}&pointX=${pointX}&pointY=${pointY}&headers=${headers}",

            type = "get",
            headers = {
                    "Accept-Charset: utf-8",
                    "Cookie: ticket=e39a060b9d0d4155a041dcf7fffb1541"
            },
            dataType = "json"
    )
    Object fireAddBuilding(
            @DataVariable("base") String base,
            @DataVariable("name") String name,
            @DataVariable("unitId") String unitId,
            @DataVariable("unitName") String unitName,
            @DataVariable("location") String location,
            @DataVariable("area") String area,
            @DataVariable("heightOfBuilding") String heightOfBuilding,
            @DataVariable("floors") String floors,
            @DataVariable("structure") String structure,
            @DataVariable("buildYear") String buildYear,
            @DataVariable("property") String property,
            @DataVariable("linkname") String linkname,
            @DataVariable("phone") String phone,
            @DataVariable("pointX") String pointX,
            @DataVariable("pointY") String pointY,
            @DataVariable("headers") String headers
    );

    /**
     * =========================== ===========================消防平台添加设备=========================== ===========================
     **/
    @Request(
            url = "${base}/device/addDevice?${params}",
            type = "get",
            headers = {
                    "Accept-Charset: utf-8",
                    "Cookie: ticket=e39a060b9d0d4155a041dcf7fffb1541"
            }
    )
    Object fireAddDeviceBak(
            @DataVariable("base") String base,
            @DataParam("params") String params //@DataVariable("params") String param
    );
    @Request(
            url = "${base}/device/addDevice?name=${name}&unitId=${unitId}&unitName=${unitName}&buildingId=${buildingId}&buildingName=${buildingName}" +
                    "&floorId=${floorId}&floorNumber=${floorNumber}&roomId=${roomId}&roomNumber=${roomNumber}&deviceTypeId=${deviceTypeId}&deviceTypeName=${deviceTypeName}" +
                    "&pointX=${pointX}&pointY=${pointY}&xRate=${xRate}" +
                    "&mac=${mac}&startDate=${startDate}&height=${height}&fheight=${fheight}&lifeMonth=${lifeMonth}&firm=${firm}&productDate=${productDate}" +
                    "&maintenanceUnit=${maintenanceUnit}&maintenanceUser=${maintenanceUser}&maintenancePhone=${maintenancePhone}&controllerId=${controllerId}" +
                    "&modelCode=${modelCode}&deviceUrl=${deviceUrl}",

            type = "get",
            headers = {
                    "Accept-Charset: utf-8",
                    "Cookie: ticket=4ef21d91fb614793a58bb85b94e66082"
            },
            dataType = "json"
    )
    Object fireAddDevice(
            @DataVariable("base") String base,
            @DataVariable("name") String name,
            @DataVariable("unitId") String unitId,
            @DataVariable("unitName") String unitName,
            @DataVariable("buildingId") String buildingId,
            @DataVariable("buildingName") String buildingName,
            @DataVariable("floorId") String floorId,
            @DataVariable("floorNumber") String floorNumber,
            @DataVariable("roomId") String roomId,
            @DataVariable("roomNumber") String roomNumber,
            @DataVariable("deviceTypeId") String deviceTypeId,
            @DataVariable("deviceTypeName") String deviceTypeName,
            @DataVariable("pointX") String pointX,
            @DataVariable("pointY") String pointY,
            @DataVariable("xRate") String xRate,
            @DataVariable("mac") String mac,
            @DataVariable("startDate") String startDate,
            @DataVariable("height") String height,
            @DataVariable("fheight") String fheight,
            @DataVariable("lifeMonth") String lifeMonth,
            @DataVariable("firm") String firm,
            @DataVariable("productDate") String productDate,
            @DataVariable("maintenanceUnit") String maintenanceUnit,
            @DataVariable("maintenanceUser") String maintenanceUser,
            @DataVariable("maintenancePhone") String maintenancePhone,
            @DataVariable("controllerId") String controllerId,
            @DataVariable("modelCode") String modelCode,
            @DataVariable("deviceUrl") String deviceUrl
    );
    /** ------------------------往消防平台添加巡检路线------------------------ **/
    /**
     * @title: 添加巡检路线 <tb>
     * @params: base  url地址<tb>
     * @params: FireInspection  对象json<tb>
     */
    @Request(
            url = "${base}",
            type = "post",
            dataType = "json",
            contentType = "application/json",
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: application/json",
                    "Cookie: ticket=4ef21d91fb614793a58bb85b94e66082"
            }
    )
    Object fireAddInspection(
            @DataVariable("base") String base,
            @DataObject FireInspection fireInspection
    );


    /**
     * ------------------------往消防平台添加设备------------------------
     **/
    @Request(
            url = "${base}?${params}",
            type = "get",
            headers = {
                    "Accept-Charset: utf-8",
                    "Cookie: ticket=4ef21d91fb614793a58bb85b94e66082"
            }
    )
    Object fireAddTroubleBak(
            @DataVariable("base") String base,
            @DataParam("params") String params //@DataVariable("params") String param
    );

    @Request(
            url = "${base}/trouble/insertTrouble?type=${type}&levels=${levels}&dangerName=${dangerName}" +
                    "&unitId=${unitId}&unitName=${unitName}&buildingId=${buildingId}&buildingName=${buildingName}" +
                    "&floorId=${floorId}&floorNumber=${floorNumber}&roomId=${roomId}&roomNumber=${roomNumber}" +
                    "&pointX=${pointX}&pointY=${pointY}&xRate=${xRate}&yRate=${yRate}" +
                    "&cont=${cont}&gridId=${gridId}",
            type = "get",
            headers = {
                    "Accept-Charset: utf-8",
                    "Cookie: ticket=4ef21d91fb614793a58bb85b94e66082"
            },
            dataType = "json"
    )
    Object fireAddTrouble(
            @DataVariable("base") String base,
            @DataVariable("type") Integer type,
            @DataVariable("levels") Integer levels,
            @DataVariable("dangerName") String dangerName,
            @DataVariable("unitId") String unitId,
            @DataVariable("unitName") String unitName,
            @DataVariable("buildingId") String buildingId,
            @DataVariable("buildingName") String buildingName,
            @DataVariable("floorId") String floorId,
            @DataVariable("floorNumber") String floorNumber,
            @DataVariable("roomId") String roomId,
            @DataVariable("roomNumber") String roomNumber,
            @DataVariable("pointX") String pointX,
            @DataVariable("pointY") String pointY,
            @DataVariable("xRate") String xRate,
            @DataVariable("yRate") String yRate,
            @DataVariable("cont") String cont,
            @DataVariable("gridId") String gridId
    );
}
