package com.lighting.business.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xu Fulin
 * @since 2019-01-26
 */
@TableName("t_lighting")
public class Lighting  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 灯杆id
     */
    @TableId(type=IdType.UUID)
    @TableField("LIGHTINGID")
    private String lightingid;

    /**
     * 灯杆序号
     */
    @TableField("ORDERNUMBER")
    private String ordernumber;

    /**
     * 灯杆名称
     */
    @TableField("LIGHTINGNAME")
    private String lightingname;

    /**
     * 区域id
     */
    @TableField("AREAID")
    private String areaid;

    /**
     * 项目id
     */
    @TableField("PROJECTID")
    private String projectid;

    /**
     * 灯杆型号
     */
    @TableField("LIGHTINGMODEL")
    private String lightingmodel;

    /**
     * 灯具id
     */
    @TableField("LAMPSID")
    private String lampsid;

    /**
     * 广告屏id
     */
    @TableField("ADSCREENID")
    private String adscreenid;

    /**
     * 摄像头id
     */
    @TableField("CAMERAID")
    private String cameraid;

    /**
     * 一键报警盒id
     */
    @TableField("ALARMBOXID")
    private String alarmboxid;

    /**
     * 气象站id
     */
    @TableField("WEATHERID")
    private String weatherid;

    /**
     * 积水传感器id
     */
    @TableField("SENSORID")
    private String sensorid;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;
    
    /**
     * 经度
     */
    @TableField("LONGITUDE")
    private Double longitude;
    
    @TableField("ISDELETED")
    private Integer isdeleted;
    /**
     * 纬度
     */
    @TableField("LATITUDE")
    private Double latitude;
    public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getLightingid() {
        return lightingid;
    }

    public void setLightingid(String lightingid) {
        this.lightingid = lightingid;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getLightingname() {
        return lightingname;
    }

    public void setLightingname(String lightingname) {
        this.lightingname = lightingname;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getLightingmodel() {
        return lightingmodel;
    }

    public void setLightingmodel(String lightingmodel) {
        this.lightingmodel = lightingmodel;
    }

    public String getLampsid() {
        return lampsid;
    }

    public void setLampsid(String lampsid) {
        this.lampsid = lampsid;
    }

    public String getAdscreenid() {
        return adscreenid;
    }

    public void setAdscreenid(String adscreenid) {
        this.adscreenid = adscreenid;
    }

    public String getCameraid() {
        return cameraid;
    }

    public void setCameraid(String cameraid) {
        this.cameraid = cameraid;
    }

    public String getAlarmboxid() {
        return alarmboxid;
    }

    public void setAlarmboxid(String alarmboxid) {
        this.alarmboxid = alarmboxid;
    }

    public String getWeatherid() {
        return weatherid;
    }

    public void setWeatherid(String weatherid) {
        this.weatherid = weatherid;
    }

    public String getSensorid() {
        return sensorid;
    }

    public void setSensorid(String sensorid) {
        this.sensorid = sensorid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Lighting [lightingid=" + lightingid + ", ordernumber=" + ordernumber + ", lightingname=" + lightingname
				+ ", areaid=" + areaid + ", projectid=" + projectid + ", lightingmodel=" + lightingmodel + ", lampsid="
				+ lampsid + ", adscreenid=" + adscreenid + ", cameraid=" + cameraid + ", alarmboxid=" + alarmboxid
				+ ", weatherid=" + weatherid + ", sensorid=" + sensorid + ", remark=" + remark + ", longitude="
				+ longitude + ", isdeleted=" + isdeleted + ", latitude=" + latitude + "]";
	}

	


}
