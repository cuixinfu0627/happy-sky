package com.happy.sky.forest;


import java.io.Serializable;

public class FireDeviceParam implements Serializable{
    /** 设备名称 **/
    private String name;

    /** 设备mac **/
    private String mac;

    /** 设备imei **/
    private String imei;

    /** 设备位置 **/
    private String location;

    /** 设备类型id **/
    private Long deviceTypeId;

    /** 设备类型名称 **/
    private String deviceTypeName;

    /** 投入时间 **/
    private Long startTime;

    /** 使用期限（月） **/
    private Integer serviceLife;

    /** 基础数据信息 */
    private Long unitId;
    private String unitName;
    private Long buildingId;
    private String buildingName;
    private Long floorId;
    private String floorName;
    private Long floorUnitId;
    private String floorUnitName;
    private Long floorRoomId;
    private String floorRoomName;

    /** 当前时间戳-签名用 */
    private Long time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getServiceLife() {
        return serviceLife;
    }

    public void setServiceLife(Integer serviceLife) {
        this.serviceLife = serviceLife;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Long getFloorUnitId() {
        return floorUnitId;
    }

    public void setFloorUnitId(Long floorUnitId) {
        this.floorUnitId = floorUnitId;
    }

    public String getFloorUnitName() {
        return floorUnitName;
    }

    public void setFloorUnitName(String floorUnitName) {
        this.floorUnitName = floorUnitName;
    }

    public Long getFloorRoomId() {
        return floorRoomId;
    }

    public void setFloorRoomId(Long floorRoomId) {
        this.floorRoomId = floorRoomId;
    }

    public String getFloorRoomName() {
        return floorRoomName;
    }

    public void setFloorRoomName(String floorRoomName) {
        this.floorRoomName = floorRoomName;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
