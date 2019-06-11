package com.lighting.business.device.entity;

import com.baomidou.mybatisplus.annotation.TableField;

public class LightingWithEvse extends Lighting {
    @TableField("DZBH")
    private String DZBH;// 编号
    @TableField("DZMC")
    private String DZMC;// 充电桩名称
    @TableField("status")
    private int status;// 状态 1在线 0不在线

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
