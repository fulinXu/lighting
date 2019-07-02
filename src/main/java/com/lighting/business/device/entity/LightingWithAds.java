package com.lighting.business.device.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

public class LightingWithAds extends Lighting{
	
	@TableField("id")
	private String id;
	/**
     * 设备卡号
     */
	@TableField("card_number")
    private String cardNumber;

    /**
     * 设备名称
     */
	@TableField("device_name")
    private String deviceName;

    /**
     * 设备宽度
     */
	@TableField("device_width")
    private Integer deviceWidth;

    /**
     * 设备高度
     */
	@TableField("device_height")
    private Integer deviceHeight;

    /**
     * 屏幕亮度（1-64）
     */
	@TableField("screen_brightness")
    private Integer screenBrightness;

    /**
     * 屏幕声音（0-15）
     */
	@TableField("screen_voice")
    private Integer screenVoice;

    /**
     * 屏幕开关
     */
	@TableField("screen_onoff")
    private Boolean screenOnoff;

    /**
     * 正在播放的节目ID
     */
	@TableField("current_program")
    private String currentProgram;

    /**
     * 网关UID
     */
	@TableField("gateway_uid")
    private String gatewayUid;

    /**
     * 经度
     */
	@TableField("longitude")
    private Double longitude;

    /**
     * 纬度
     */
	@TableField("latitude")
    private Double latitude;

    /**
     * 物理位置
     */
	@TableField("physical_location")
    private String physicalLocation;

    /**
     * 运行状态
     */
	@TableField("running_state")
    private Integer runningState;

    /**
     * 设备类型
     */
	@TableField("device_type")
    private Integer deviceType;

    /**
     * 项目ID
     */
	@TableField("project_id")
    private String projectId;

    /**
     * 区域ID
     */
	@TableField("area_id")
    private String areaId;

	/**
	 * 灯杆经度
	 */
	@TableField("lightingLatitude")
	private String lightingLatitude;

	/**
	 * 灯杆纬度
	 */
	@TableField("lightinglongitude")
	private String lightinglongitude;
	
    /**
     * 数据同步时间
     */
	@TableField("sync_time")
    private Date syncTime;
	@TableField("create_time")
    private  Date creatTime;
	@TableField("update_time")
    private  Date updateTime;
	@TableField("deleted")
    private  Boolean deleted;
	@TableField("address")
	private  String address;

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

	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Integer getDeviceWidth() {
		return deviceWidth;
	}
	public void setDeviceWidth(Integer deviceWidth) {
		this.deviceWidth = deviceWidth;
	}
	public Integer getDeviceHeight() {
		return deviceHeight;
	}
	public void setDeviceHeight(Integer deviceHeight) {
		this.deviceHeight = deviceHeight;
	}
	public Integer getScreenBrightness() {
		return screenBrightness;
	}
	public void setScreenBrightness(Integer screenBrightness) {
		this.screenBrightness = screenBrightness;
	}
	public Integer getScreenVoice() {
		return screenVoice;
	}
	public void setScreenVoice(Integer screenVoice) {
		this.screenVoice = screenVoice;
	}
	public Boolean getScreenOnoff() {
		return screenOnoff;
	}
	public void setScreenOnoff(Boolean screenOnoff) {
		this.screenOnoff = screenOnoff;
	}
	public String getCurrentProgram() {
		return currentProgram;
	}
	public void setCurrentProgram(String currentProgram) {
		this.currentProgram = currentProgram;
	}
	public String getGatewayUid() {
		return gatewayUid;
	}
	public void setGatewayUid(String gatewayUid) {
		this.gatewayUid = gatewayUid;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getPhysicalLocation() {
		return physicalLocation;
	}
	public void setPhysicalLocation(String physicalLocation) {
		this.physicalLocation = physicalLocation;
	}
	public Integer getRunningState() {
		return runningState;
	}
	public void setRunningState(Integer runningState) {
		this.runningState = runningState;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public Date getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
