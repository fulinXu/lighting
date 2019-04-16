package com.lighting.business.device.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

public class LightingWithSensor extends Lighting{
	@TableField("device_id")
	private String deviceId;
	@TableField("project_id")
	private String projectId;
	@TableField("area_id")
	private String areaId;
	@TableField("device_name")
	private String deviceName;
	@TableField("status")
	private Integer status;
	@TableField("temperature")
	private Double temperature;
	@TableField("wind_direction")
	private Double windDirection;
	@TableField("wind_power")
	private Double windPower;
	@TableField("humidity")
	private Double humidity;
	@TableField("atmospheric_pressure")
	private Double atmosphericPressure;
	@TableField("weatherrecordtime")
	private Date weatherrecordtime;
	@TableField("waterrecordtime")
	private Date waterrecordtime;
	@TableField("watersupplierid")
	private String watersupplierid;
	@TableField("initial")
	private String initial;
	@TableField("water")
	private Double water;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(Double windDirection) {
		this.windDirection = windDirection;
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
	public Double getAtmosphericPressure() {
		return atmosphericPressure;
	}
	public void setAtmosphericPressure(Double atmosphericPressure) {
		this.atmosphericPressure = atmosphericPressure;
	}
	public Date getWeatherrecordtime() {
		return weatherrecordtime;
	}
	public void setWeatherrecordtime(Date weatherrecordtime) {
		this.weatherrecordtime = weatherrecordtime;
	}
	public Date getWaterrecordtime() {
		return waterrecordtime;
	}
	public void setWaterrecordtime(Date waterrecordtime) {
		this.waterrecordtime = waterrecordtime;
	}
	public String getWatersupplierid() {
		return watersupplierid;
	}
	public void setWatersupplierid(String watersupplierid) {
		this.watersupplierid = watersupplierid;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public Double getWater() {
		return water;
	}
	public void setWater(Double water) {
		this.water = water;
	}
	
}
