package com.lighting.business.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Repository;

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
@Document(indexName = "smartcity3307",type = "t_lighting")
//@Repository
public class Lighting  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 灯杆id
     */
    @TableId(type=IdType.UUID)
    @TableField("LIGHTINGID")
    @Id
    private String lightingid;

    /**
     * 灯杆序号
     */
    @TableField("ORDERNUMBER")
    @Field(type = FieldType.Text)
    private String ordernumber;

    /**
     * 灯杆名称
     */
    @TableField("LIGHTINGNAME")
    @Field(type = FieldType.Text)
    private String lightingname;

    /**
     * 区域id
     */
    @TableField("AREAID")
    @Field(type = FieldType.Text)
    private String areaid;

    /**
     * 项目id
     */
    @TableField("PROJECTID")
    @Field(type = FieldType.Text)
    private String projectid;

    /**
     * 灯杆型号
     */
    @TableField("LIGHTINGMODEL")
    @Field(type = FieldType.Text)
    private String lightingmodel;

    /**
     * 灯具id
     */
    @TableField("LAMPSID")
    @Field(type = FieldType.Text)
    private String lampsid;

    /**
     * 广告屏id
     */
    @TableField("ADSCREENID")
    @Field(type = FieldType.Text)
    private String adscreenid;

    /**
     * 摄像头id
     */
    @TableField("CAMERAID")
    @Field(type = FieldType.Text)
    private String cameraid;

    /**
     * 一键报警盒id
     */
    @TableField("ALARMBOXID")
    @Field(type = FieldType.Text)
    private String alarmboxid;

    /**
     * 气象站id
     */
    @TableField("WEATHERID")
    @Field(type = FieldType.Text)
    private String weatherid;

    /**
     * 积水传感器id
     */
    @TableField("SENSORID")
    @Field(type = FieldType.Text)
    private String sensorid;

    /**
     * 备注
     */
    @TableField("REMARK")
    @Field(type = FieldType.Text)
    private String remark;
    
    /**
     * 经度
     */
    @TableField("LONGITUDE")
    @Field(type = FieldType.Float)
    private Double longitude;
    
    @TableField("ISDELETED")
    @Field(type = FieldType.Long)
    private Integer isdeleted;

    @TableField("address")
    @Field(type = FieldType.Text)
    private String address;
    /**
     * 纬度
     */
    @TableField("LATITUDE")
    @Field(type = FieldType.Float)
    private Double latitude;

    public Lighting(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, double v, int i, String s13, double v1) {
    }

    public Lighting() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
