package com.lighting.business.device.entity;

import java.math.BigDecimal;
import java.util.Date;

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
	@TableField("cameraState")
	private  Integer cameraState;
	@TableField("sensorStatus")
	private  Integer sensorStatus;
	@TableField("alarmboxName")
	private String alarmboxName;
	@TableField("humidity")
	private  Double humidity;
	@TableField("water")
	private  Double water;
	@TableField("temperature")
	private  Double temperature;
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
	@TableField("platformId")
	private String platformId;
	@TableField("cardNumber")
	private String cardNumber;
	@TableField("voltage")
	private String voltage;
	@TableField("currentLevel")
	private String currentLevel;
	@TableField("powerfactor")
	private String powerfactor;
	@TableField("verifyCode")
	private String verifyCode;
    @TableField("alarmState")
    private Integer alarmState;
    @TableField("cameraUuid")
	private String cameraUuid;
    @TableField("alarmboxUuid")
    private String alarmboxUuid;
	@TableField("DZBH")
	private String DZBH;// 编号
	@TableField("DZMC")
	private String DZMC;// 充电桩名称
	@TableField("evseStatus")
	private int evseStatus;// 状态 1在线 0不在线
	@TableField("GZZT")
	private String GZZT; //工作状态

	public String getDZBH() {
		return DZBH;
	}

	public void setDZBH(String DZBH) {
		this.DZBH = DZBH;
	}

	public String getDZMC() {
		return DZMC;
	}

	public void setDZMC(String DZMC) {
		this.DZMC = DZMC;
	}

	public int getEvseStatus() {
		return evseStatus;
	}

	public void setEvseStatus(int evseStatus) {
		this.evseStatus = evseStatus;
	}

	public String getGZZT() {
		return GZZT;
	}

	public void setGZZT(String GZZT) {
		this.GZZT = GZZT;
	}

	public String getCameraUuid() {
		return cameraUuid;
	}

	public void setCameraUuid(String cameraUuid) {
		this.cameraUuid = cameraUuid;
	}

	public String getAlarmboxUuid() {
		return alarmboxUuid;
	}

	public void setAlarmboxUuid(String alarmboxUuid) {
		this.alarmboxUuid = alarmboxUuid;
	}

	public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getAlarmState() {
        return alarmState;
    }

    public void setAlarmState(Integer alarmState) {
        this.alarmState = alarmState;
    }

    public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}
	public String getPowerfactor() {
		return powerfactor;
	}
	public void setPowerfactor(String powerfactor) {
		this.powerfactor = powerfactor;
	}
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
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
}
