package com.lighting.business.device.service;

import com.lighting.business.device.entity.*;

import landsky.basic.common.ResultWrapper;
import landsky.basic.entity.UserHolder;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xu Fulin
 * @since 2019-01-21
 */
public interface ILightingService extends IService<Lighting> {
	
	IPage<LightingWithOthers> getLightingList(Page<LightingWithOthers> page, QueryWrapper<Lighting> wrapper,UserHolder user);
	List<LightingWithOthers> getLightingListById(QueryWrapper<Lighting> wrapper,UserHolder user);
	IPage<LightingWithLamps> getLampsListByLighting(Page<LightingWithLamps> page, QueryWrapper<Lighting> wrapper,UserHolder user);
	IPage<LightingWithAds> getAdsListByLighting(Page<LightingWithAds> page, QueryWrapper<Lighting> wrapper,UserHolder user);
	IPage<LightingWithCamera> getCameraListByLighting(Page<LightingWithCamera> page, QueryWrapper<Lighting> wrapper,UserHolder user);
	IPage<LightingWithAlarm> getAlarmListByLighting(Page<LightingWithAlarm> page, QueryWrapper<Lighting> wrapper,UserHolder user);
	IPage<LightingWithSensor> getSensorListByLighting(Page<LightingWithSensor> page, QueryWrapper<Lighting> wrapper,UserHolder user);
	IPage<LightingWithSensor> getWeatherListByLighting(Page<LightingWithSensor> page, QueryWrapper<Lighting> wrapper,UserHolder user);
	IPage<LightingWithSensor> getWaterListByLighting(Page<LightingWithSensor> page, QueryWrapper<Lighting> wrapper,UserHolder user);
	IPage<LightingWithEvse> getEvseListByLighting(Page<LightingWithEvse> page, QueryWrapper<Lighting> wrapper, UserHolder user);
	boolean adscreenBind(String adscreenId,QueryWrapper<Object> wrapper,String bindid);
	boolean alarmboxBind(String alarmId,QueryWrapper<Object> wrapper,String bindid);
	boolean cameraBind(String cameraId,QueryWrapper<Object> wrapper,String bindid);
	boolean brightBind(String brightId,QueryWrapper<Object> wrapper,String bindid);
	boolean envirBind(String envirId,QueryWrapper<Object> wrapper,String bindid);
	boolean lightingLoseAdscreen(QueryWrapper<Object> wrapper,String bindid);
	boolean adscreenLoseBind(QueryWrapper<Object> wrapper,String adscreenid);
	boolean lightingLoseAlarmbox(QueryWrapper<Object> wrapper,String bindid);
	boolean alarmboxLoseBind(QueryWrapper<Object> wrapper,String alarmboxid);
	boolean lightingLoseCamera(QueryWrapper<Object> wrapper,String bindid);
	boolean cameraLoseBind(QueryWrapper<Object> wrapper,String cameraid);
	boolean lightingLoseBright(QueryWrapper<Object> wrapper,String bindid);
	boolean brightLoseBind(QueryWrapper<Object> wrapper,String brightid);
	boolean lightingLoseSensor(QueryWrapper<Object> wrapper,String bindid);
	boolean sensorLoseBind(QueryWrapper<Object> wrapper,String brightid);
	IPage<LightingWithLamps> getBrightNotBind(Page<LightingWithLamps> page,UserHolder user,Lighting lighting);
	IPage<LightingWithAds> getAdscreenNotBind(Page<LightingWithAds> page,UserHolder user,Lighting lighting);
	IPage<LightingWithCamera> getCameraNotBind(Page<LightingWithCamera> page,UserHolder user,Lighting lighting);
	IPage<LightingWithAlarm> getAlarmNotBind(Page<LightingWithAlarm> page,UserHolder user,Lighting lighting);
	IPage<LightingWithSensor> getSensorNotBind(Page<LightingWithSensor> page,UserHolder user,Lighting lighting);
	List<Map<String,Object>> getLightingByIds(List<String> lightingIds,Lighting lighting);
	Map<String,Object> getLighting(Lighting lighting,QueryWrapper<Lighting> wrapper);
	ResultWrapper getCountByAreaId(QueryWrapper<Lighting> wrapper,UserHolder user);
	ResultWrapper getLightingListAll(UserHolder user,String projectId,String areaId);
	ResultWrapper delLightingByIsdel(List<String> lightingList);
	List<Map<String,String>> getSensorsByLighting(String areaId,String projectId,int pageIndex,int pageSize);
	int getTotalSensorsByLighting(String areaId,String projectId);
	List<String> getSensorIdsByLighting(String areaId,String projectId);
	String getSensorIdByLighting(String areaId,String projectId,String lightingid);
	List<Object> getIPCIds(QueryWrapper<Lighting> wrapper);
	Boolean isBinding(QueryWrapper<Lighting> wrapper);
	Boolean isEqArea(String[] deviceIds,String projectid,String areaid);
	Map<String,Double> getCoordinateByDevice(QueryWrapper<Lighting> wrapper);
}
