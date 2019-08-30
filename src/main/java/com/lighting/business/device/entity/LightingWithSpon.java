package com.lighting.business.device.entity;

import com.baomidou.mybatisplus.annotation.TableField;

public class LightingWithSpon extends Lighting{
    /**
     * 音柱id
     */
    @TableField("id")
    private String id;

    /**
     * 音柱名称
     */
    private String tname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
