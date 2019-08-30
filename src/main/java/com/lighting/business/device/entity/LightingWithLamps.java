package com.lighting.business.device.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import landsky.basic.cache.project.ProjectAreaCacheable;

public class LightingWithLamps extends Lighting implements ProjectAreaCacheable {
	@TableField("NODE_ID")
    private String nodeId;
	@TableField("DEVICE_ID")
    private String deviceId;
	@TableField("VERIFY_CODE")
    private String verifyCode;
	@TableField("DEVICE_TYPE")
    private String deviceType;
	@TableField("PROTOCOL_TYPE")
    private String protocolType;
	@TableField("ALIAS")
    private String alias;
	@TableField("CREATE_TIME")
    private Date createTime;
	@TableField("MANUFACTURER_ID")
    private String manufacturerId;
	@TableField("MODEL")
    private String model;
	@TableField("STATUS")
    private String status;
	@TableField("ON_OFF")
    private Integer onOff;
	@TableField("BRIGHTNESS")
    private Double brightness;
	@TableField("VOLTAGE")
    private Double voltage;
	@TableField("CURRENT_LEVEL")
    private Double currentLevel;
	@TableField("POWERFACTOR")
    private Double powerfactor;
	@TableField("FREQUENCY")
    private Double frequency;
	@TableField("GROUP_ID")
    private String groupId;
	@TableField("RELAY_LIGHT")
    private Integer relayLight;
	@TableField("RELAY_ID")
    private String relayId;
	@TableField("LONGITUDE")
    private Double longitude;
	@TableField("LATITUDE")
    private Double latitude;
	@TableField("ORG_ID")
    private String orgId;
	@TableField("SIGNALPOWER")
    private Double signalpower;
	@TableField("SNR")
    private Double snr;
	@TableField("ECL")
    private Double ecl;
	@TableField("TYPE")
    private Integer type;
	@TableField("POWER")
    private Double power;
	@TableField("OPEN_ID")
    private String openId;
	@TableField("PLATFORM_ID")
    private String platformId;
	@TableField("IMSI")
    private String imsi;
	@TableField("ACTIVE_STATE")
    private Integer activeState;
	@TableField("AD")
    private Integer ad;
	@TableField("RSSI")
    private Integer rssi;
	@TableField("PCI")
    private Integer pci;
	@TableField("RSRQ")
    private Integer rsrq;
	@TableField("CSQ")
    private Integer csq;
	@TableField("STATE")
    private Integer state;
	@TableField("isfault")
	private Integer isfault;
	@TableField("UPLOAD_TIME")
	private Date uploadTime;
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

	public String getLightingLatitude() {
		return lightingLatitude;
	}

	@TableField("address")
	private  String address;

	@TableField(exist = false)
	private String projectName;

	@TableField(exist = false)
	private String areaName;

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getOnOff() {
		return onOff;
	}
	public void setOnOff(Integer onOff) {
		this.onOff = onOff;
	}
	public Double getBrightness() {
		return brightness;
	}
	public void setBrightness(Double brightness) {
		this.brightness = brightness;
	}
	public Double getVoltage() {
		return voltage;
	}
	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}
	public Double getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(Double currentLevel) {
		this.currentLevel = currentLevel;
	}
	public Double getPowerfactor() {
		return powerfactor;
	}
	public void setPowerfactor(Double powerfactor) {
		this.powerfactor = powerfactor;
	}
	public Double getFrequency() {
		return frequency;
	}
	public void setFrequency(Double frequency) {
		this.frequency = frequency;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Integer getRelayLight() {
		return relayLight;
	}
	public void setRelayLight(Integer relayLight) {
		this.relayLight = relayLight;
	}
	public String getRelayId() {
		return relayId;
	}
	public void setRelayId(String relayId) {
		this.relayId = relayId;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Double getSignalpower() {
		return signalpower;
	}
	public void setSignalpower(Double signalpower) {
		this.signalpower = signalpower;
	}
	public Double getSnr() {
		return snr;
	}
	public void setSnr(Double snr) {
		this.snr = snr;
	}
	public Double getEcl() {
		return ecl;
	}
	public void setEcl(Double ecl) {
		this.ecl = ecl;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getPower() {
		return power;
	}
	public void setPower(Double power) {
		this.power = power;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public Integer getActiveState() {
		return activeState;
	}
	public void setActiveState(Integer activeState) {
		this.activeState = activeState;
	}
	public Integer getAd() {
		return ad;
	}
	public void setAd(Integer ad) {
		this.ad = ad;
	}
	public Integer getRssi() {
		return rssi;
	}
	public void setRssi(Integer rssi) {
		this.rssi = rssi;
	}
	public Integer getPci() {
		return pci;
	}
	public void setPci(Integer pci) {
		this.pci = pci;
	}
	public Integer getRsrq() {
		return rsrq;
	}
	public void setRsrq(Integer rsrq) {
		this.rsrq = rsrq;
	}
	public Integer getCsq() {
		return csq;
	}
	public void setCsq(Integer csq) {
		this.csq = csq;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String getProjectId() {
		return getProjectid();
	}

	public Integer getIsfault() {
		return isfault;
	}

	public void setIsfault(Integer isfault) {
		this.isfault = isfault;
	}

	@Override
	public String getAreaId() {
		return getAreaid();
	}

	public String getProjectName() {
		return projectName;
	}

	public String getAreaName() {
		return areaName;
	}
}
