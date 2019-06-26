package com.lighting.business.device.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;

public class LightingWithCamera extends Lighting{
	/**
	 * @Fields projectId:项目id
	 */
	@TableField("projectId")
	private String projectId;

	/**
	 * @Fields areaId:区域id
	 */
	@TableField("areaId")
	private String areaId;
	
	/**
	 * @Fields deviceNumber:设备编号
	 */
	@TableField("deviceNumber")
	private String deviceNumber;

	/**
	 * @Fields deviceSequence:设备序列号(唯一标识)
	 */
	@TableField("deviceSequence")
	private String deviceSequence;

	/**
	 * @Fields deviceIp:设备ip地址
	 */
	@TableField("deviceIp")
	private String deviceIp;

	/**
	 * @Fields devicePort:设备端口
	 */
	@TableField("devicePort")
	private String devicePort;
	
	/**
	 * @Fields url:预览取流URL
	 */
	@TableField("url")
	private String url;
	
	/**
	 * @Fields deviceAlias:设备别名
	 */
	@TableField("deviceAlias")
	private String deviceAlias;

	/**
	 * @Fields unitPrice:单价
	 */
	@TableField("unitPrice")
	private Double unitPrice;
	
	/**
	 * @Fields memory:内存容量
	 */
	@TableField("memory")
	private String memory;
	
	/**
	 * @Fields modelNumber:型号
	 */
	@TableField("modelNumber")
	private String modelNumber;
	
	/**
	 * @Fields brand:品牌
	 */
	@TableField("brand")
	private String brand;

	/**
	 * @Fields state:状态
	 */
	@TableField("state")
	private Integer state;
	@TableField("id")
	private String  id;

	@TableField("camera_uuid")
	private String cameraUuid;
	/**
	 * @Fields remark:备注
	 */
	@TableField("remark")
	private String remark;
	@TableField("createTime")
	private Date createTime;
	@TableField("updateTime")
	private Date updateTime;
	@TableField("deleted")
	private Boolean deleted;

	public String getCameraUuid() {
		return cameraUuid;
	}

	public void setCameraUuid(String cameraUuid) {
		this.cameraUuid = cameraUuid;
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
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public String getDeviceSequence() {
		return deviceSequence;
	}
	public void setDeviceSequence(String deviceSequence) {
		this.deviceSequence = deviceSequence;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getDevicePort() {
		return devicePort;
	}
	public void setDevicePort(String devicePort) {
		this.devicePort = devicePort;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDeviceAlias() {
		return deviceAlias;
	}
	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
