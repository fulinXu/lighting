package com.lighting.business.device.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;

public class LightingWithOthers extends Lighting{
	@TableField("alias")
    private String alias;
	@TableField("adscreenName")
    private String adscreenName;
	@TableField("device_alias")
    private String deviceAlias;
	@TableField("sensorName")
    private String sensorName;
	@TableField("status")
	private String status;
	@TableField("ON_OFF")
	private Integer onOff;
	@TableField("NODE_ID")
	private String nodeId;
	@TableField("BRIGHTNESS")
	private  BigDecimal brightness;
	@TableField("deviceAliasc")
	private  String deviceAliasc;
	@TableField("camera_state")
	private  Integer cameraState;
	@TableField("temperature")
	private  Double temperature;
	@TableField("wind_power")
	private  Double windPower;
	@TableField("humidity")
	private  Double humidity;
	@TableField("water")
	private  Double water;
	@TableField("sensorStatus")
	private  Integer sensorStatus;
	@TableField("alarmboxName")
	private String alarmboxName;
	public Integer getSensorStatus() {
		return sensorStatus;
	}
	public void setSensorStatus(Integer sensorStatus) {
		this.sensorStatus = sensorStatus;
	}
	public void setCameraState(Integer cameraState) {
		this.cameraState = cameraState;
	}
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public String getDeviceAliasc() {
		return deviceAliasc;
	}
	public void setDeviceAliasc(String deviceAliasc) {
		this.deviceAliasc = deviceAliasc;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getWindPower() {
		return windPower;
	}
	public void setWindPower(Double windPower) {
		this.windPower = windPower;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Double getWater() {
		return water;
	}
	public void setWater(Double water) {
		this.water = water;
	}
	public BigDecimal getBrightness() {
		return brightness;
	}
	public void setBrightness(BigDecimal brightness) {
		this.brightness = brightness;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAdscreenName() {
		return adscreenName;
	}
	public void setAdscreenName(String adscreenName) {
		this.adscreenName = adscreenName;
	}
	
	public String getDeviceAlias() {
		return deviceAlias;
	}
	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}
	public Integer getCameraState() {
		return cameraState;
	}
	public Integer getOnOff() {
		return onOff;
	}
	public void setOnOff(Integer onOff) {
		this.onOff = onOff;
	}
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeID(String nodeID) {
		this.nodeId = nodeID;
	}
	public String getAlarmboxName() {
		return alarmboxName;
	}
	public void setAlarmboxName(String alarmboxName) {
		this.alarmboxName = alarmboxName;
	}
	
}
