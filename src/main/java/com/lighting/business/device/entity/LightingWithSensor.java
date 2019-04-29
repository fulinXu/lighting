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
	@TableField("humidity")
	private Double humidity;
	@TableField("noise")
	private Double noise;
	@TableField("illuminate")
	private Double illuminate;
	@TableField("densityGas")
	private Double densityGas;
	@TableField("co2")
	private Double co2;
	@TableField("humiditySoil")
	private Double humiditySoil;
	@TableField("temperatureSoil")
	private Double temperatureSoil;
	@TableField("pm25")
	private Double pm25;
	@TableField("pm10")
	private Double pm10;
	@TableField("atmosphericPressure")
	private Double atmosphericPressure;
	@TableField("weatherCreateTime")
	private Date weatherCreateTime;
	@TableField("waterCreateTime")
	private Date waterCreateTime;
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
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Double getNoise() {
		return noise;
	}
	public void setNoise(Double noise) {
		this.noise = noise;
	}
	public Double getIlluminate() {
		return illuminate;
	}
	public void setIlluminate(Double illuminate) {
		this.illuminate = illuminate;
	}
	public Double getDensityGas() {
		return densityGas;
	}
	public void setDensityGas(Double densityGas) {
		this.densityGas = densityGas;
	}
	public Double getCo2() {
		return co2;
	}
	public void setCo2(Double co2) {
		this.co2 = co2;
	}
	public Double getHumiditySoil() {
		return humiditySoil;
	}
	public void setHumiditySoil(Double humiditySoil) {
		this.humiditySoil = humiditySoil;
	}
	public Double getTemperatureSoil() {
		return temperatureSoil;
	}
	public void setTemperatureSoil(Double temperatureSoil) {
		this.temperatureSoil = temperatureSoil;
	}
	public Double getPm25() {
		return pm25;
	}
	public void setPm25(Double pm25) {
		this.pm25 = pm25;
	}
	public Double getPm10() {
		return pm10;
	}
	public void setPm10(Double pm10) {
		this.pm10 = pm10;
	}
	public Double getAtmosphericPressure() {
		return atmosphericPressure;
	}
	public void setAtmosphericPressure(Double atmosphericPressure) {
		this.atmosphericPressure = atmosphericPressure;
	}
	public Date getWeatherCreateTime() {
		return weatherCreateTime;
	}
	public void setWeatherCreateTime(Date weatherCreateTime) {
		this.weatherCreateTime = weatherCreateTime;
	}
	public Date getWaterCreateTime() {
		return waterCreateTime;
	}
	public void setWaterCreateTime(Date waterCreateTime) {
		this.waterCreateTime = waterCreateTime;
	}
	public Double getWater() {
		return water;
	}
	public void setWater(Double water) {
		this.water = water;
	}
	
	
}
