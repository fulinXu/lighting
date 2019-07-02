package com.lighting.business.device.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;

public class LightingWithAlarm extends Lighting{
	/**
	 * @Fields projectId:项目id
	 */
	@TableField("project_id")
	private String projectId;

	/**
	 * @Fields areaId:区域id
	 */
	@TableField("area_id")
	private String areaId;

	/**
	 * @Fields deviceNumber:设备编号(设备唯一标识)
	 */
	@TableField("device_number")
	private String deviceNumber;

	/**
	 * @Fields deviceSequence:设备序列号(唯一标识)
	 */
	@TableField("device_sequence")
	private String deviceSequence;

	/**
	 * @Fields deviceIp:设备ip地址
	 */
	@TableField("device_ip")
	private String deviceIp;

	/**
	 * @Fields devicePort:设备端口
	 */
	@TableField("device_port")
	private String devicePort;

	/**
	 * @Fields deviceAlias:设备别名
	 */
	@TableField("device_alias")
	private String deviceAlias;

	/**
	 * @Fields modelNumber:型号
	 */
	@TableField("model_number")
	private String modelNumber;

	@TableField("camera_uuid")
	private String cameraUuid;
	/**
	 * @Fields memory:内存容量
	 */
	@TableField("memory")
	private String memory;

	/**
	 * @Fields modelNumber:(解除)布防
	 */
	@TableField("relieved")
	private Integer relieved;
	/**
	 * @Fields state:状态
	 */
	@TableField("state")
	private Integer state;

	@TableField("id")
	private String id;
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
	public String getDeviceAlias() {
		return deviceAlias;
	}
	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getRelieved() {
		return relieved;
	}
	public void setRelieved(Integer relieved) {
		this.relieved = relieved;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
