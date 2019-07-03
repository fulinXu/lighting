package com.lighting.business.device.entity;

import com.baomidou.mybatisplus.annotation.TableField;

public class LightingWithEvse extends Lighting {
    @TableField("DZBH")
    private String DZBH;// 编号
    @TableField("DZMC")
    private String DZMC;// 充电桩名称
    @TableField("status")
    private int status;// 状态 1在线 0不在线
    @TableField("GZZT")
    private String GZZT; //工作状态
    /**
     * 灯杆经度
     */
    @TableField("lightingLatitude")
    private String lightingLatitude;
    @TableField("startTime")
    private String startTime;
    @TableField("endTime")
    private String endTime;
    /**
     * 灯杆纬度
     */
    @TableField("lightinglongitude")
    private String lightinglongitude;

    @TableField("address")
    private  String address;

    public String getGZZT() {
        return GZZT;
    }

    public void setGZZT(String GZZT) {
        this.GZZT = GZZT;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getLightingLatitude() {
        return lightingLatitude;
    }

    public void setLightingLatitude(String lightingLatitude) {
        this.lightingLatitude = lightingLatitude;
    }

    public String getLightinglongitude() {
        return lightinglongitude;
    }

    public void setLightinglongitude(String lightinglongitude) {
        this.lightinglongitude = lightinglongitude;
    }
    public String getDZBH() {
        return DZBH;
    }

    public String getDZMC() {
        return DZMC;
    }

    public int getStatus() {
        return status;
    }

    public void setDZBH(String DZBH) {
        this.DZBH = DZBH;
    }

    public void setDZMC(String DZMC) {
        this.DZMC = DZMC;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
