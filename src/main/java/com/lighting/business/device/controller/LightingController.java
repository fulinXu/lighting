package com.lighting.business.device.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import landsky.basic.feign.envir.EnvirFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lighting.business.device.entity.Lighting;
import com.lighting.business.device.entity.LightingWithAds;
import com.lighting.business.device.entity.LightingWithAlarm;
import com.lighting.business.device.entity.LightingWithCamera;
import com.lighting.business.device.entity.LightingWithLamps;
import com.lighting.business.device.entity.LightingWithOthers;
import com.lighting.business.device.entity.LightingWithSensor;
import com.lighting.business.device.service.ILightingService;
import com.lighting.business.utils.EsClientUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import landsky.basic.common.ResultWrapper;
import landsky.basic.common.UUIDUtils;
import landsky.basic.entity.UserHolder;
import landsky.basic.log.LogType;
import landsky.basic.annotation.LogProcessor;
import landsky.basic.common.BaseController;
import landsky.basic.common.JacksonUtils;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xu Fulin
 * @since 2019-01-21
 */
@RestController
@RequestMapping("/lighting")
public class LightingController extends  BaseController{
	
	// 定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger log = LoggerFactory.getLogger(Lighting.class);
	
	@Autowired
	private ILightingService lightingService;
	@Autowired
	private EsClientUtil esClientUtil;
	@Autowired
	private EnvirFeignService sensorFeign;
	
	@ApiOperation("获取灯杆的信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(dataType = "String",name = "lightingid", value = "灯杆Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "user-holder", value = "请求头", paramType = "header", required = true)
		,@ApiImplicitParam(dataType = "String",name = "ordernumber", value = "序号", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "lightingname", value = "灯杆名称", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "areaid", value = "区域ID", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "projectid", value = "项目Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "lightmodel", value = "灯杆型号", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "lampsid", value = "灯具Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "adscreenid", value = "广告屏Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "cameraid", value = "摄像头Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "alarmboxid", value = "一键报警盒Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "sensorid", value = "传感器Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "weatherid", value = "气象Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "remark", value = "备注", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "Double",name = "longitude", value = "经度", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "Double",name = "latitude", value = "纬度", paramType = "query", required = false)
	})
	@GetMapping("/getLightingList")
	public  IPage<LightingWithOthers> getLightingList(Page<LightingWithOthers> page, Lighting lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getLightingname()!=null&&!"".equals(lighting.getLightingname())) {
			wrapper.like("l.lightingname", lighting.getLightingname());
		}
		if (lighting.getAdscreenid()!=null&&!lighting.getAdscreenid().equals("")) {
			wrapper.eq("l.ADSCREENID",lighting.getAdscreenid());
		}
		if (lighting.getLampsid()!=null&&!lighting.getLampsid().equals("")) {
			wrapper.eq("l.LAMPSID",lighting.getLampsid());
		}
		if (lighting.getCameraid()!=null&&!lighting.getCameraid().equals("")) {
			wrapper.eq("l.CAMERAID",lighting.getCameraid());
		}
		if (lighting.getAlarmboxid()!=null&&!lighting.getAlarmboxid().equals("")) {
			wrapper.eq("l.ALARMBOXID",lighting.getAlarmboxid());
		}
		if (lighting.getSensorid()!=null&&!lighting.getSensorid().equals("")) {
			wrapper.eq("l.SENSORID",lighting.getSensorid());
		}
		if (lighting.getWeatherid()!=null&&!lighting.getWeatherid().equals("")) {
			wrapper.eq("l.WEATHERID",lighting.getWeatherid());
		}
		if (lighting.getAreaid()!=null&&!lighting.getAreaid().equals("")) {
			wrapper.eq("l.AREAID",lighting.getAreaid());
		}
		if (lighting.getProjectid()!=null&&!lighting.getProjectid().equals("")) {
			wrapper.eq("l.PROJECTID",lighting.getProjectid());
		}
		if (lighting.getLightingid()!=null&&!lighting.getLightingid().equals("")) {
			wrapper.eq("l.LIGHTINGID",lighting.getLightingid());
		}
		wrapper.eq("l.isdeleted",0);
		return lightingService.getLightingList(page, wrapper,getUser());
	}	
	
	
	@ApiOperation("通过灯杆获取灯具的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getLampsListByLighting")
	public  IPage<LightingWithLamps> getLampsListByLighting(Page<LightingWithLamps> page, Lighting lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		return lightingService.getLampsListByLighting(page, wrapper,getUser());
	}
	
	@ApiOperation("通过灯杆获取广告屏的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getAdsListByLighting")
	public  IPage<LightingWithAds> getAdsListByLighting(Page<LightingWithAds> page, Lighting lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		return lightingService.getAdsListByLighting(page, wrapper, getUser());
	}
	
	@ApiOperation("通过灯杆获取摄像头的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getCameraListByLighting")
	public  IPage<LightingWithCamera> getCameraListByLighting(Page<LightingWithCamera> page, Lighting lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		return lightingService.getCameraListByLighting(page, wrapper, getUser());
	}
	
	@ApiOperation("通过灯杆获取报警盒的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getAlarmListByLighting")
	public  IPage<LightingWithAlarm> getAlarmListByLighting(Page<LightingWithAlarm> page, Lighting lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		return lightingService.getAlarmListByLighting(page, wrapper, getUser());
}


	@ApiOperation("通过灯杆获取传感器的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getSensorListByLighting")
	public  IPage<LightingWithSensor> getSensorListByLighting(Page<LightingWithSensor> page, Lighting lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		return lightingService.getSensorListByLighting(page, wrapper, getUser());
	}
	
	@ApiOperation("通过模糊查询灯杆获取灯具的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getLampsListByLightingLike")
	public  IPage<LightingWithLamps> getLampsListByLightingLike(Page<LightingWithLamps> page, LightingWithLamps lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getAlias()!=null&&!"".equals(lighting.getAlias())) {
			wrapper.like("n.alias",lighting.getAlias());
		}
		return lightingService.getLampsListByLighting(page, wrapper,getUser());
	}
	
	@ApiOperation("通过模糊查询灯杆获取广告屏的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getAdsListByLightingLike")
	public  IPage<LightingWithAds> getAdsListByLightingLike(Page<LightingWithAds> page, LightingWithAds lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getDeviceName()!=null&&!"".equals(lighting.getDeviceName())) {
			wrapper.like("n.device_name",lighting.getDeviceName());
		}
		return lightingService.getAdsListByLighting(page, wrapper, getUser());
	}
	
	@ApiOperation("通过模糊查询灯杆获取摄像头的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getCameraListByLightingLike")
	public  IPage<LightingWithCamera> getCameraListByLightingLike(Page<LightingWithCamera> page, LightingWithCamera lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getDeviceAlias()!=null&&!"".equals(lighting.getDeviceAlias())) {
			wrapper.like("n.device_alias",lighting.getDeviceAlias());
		}
		return lightingService.getCameraListByLighting(page, wrapper, getUser());
	}
	
	@ApiOperation("通过模糊查询灯杆获取报警盒的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getAlarmListByLightingLike")
	public  IPage<LightingWithAlarm> getAlarmListByLightingLike(Page<LightingWithAlarm> page, LightingWithAlarm lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getDeviceAlias()!=null&&!"".equals(lighting.getDeviceAlias())) {
			wrapper.like("n.device_alias",lighting.getDeviceAlias());
		}
		return lightingService.getAlarmListByLighting(page, wrapper, getUser());
}


	@ApiOperation("通过模糊查询灯杆获取传感器的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getSensorListByLightingLike")
	public  IPage<LightingWithSensor> getSensorListByLightingLike(Page<LightingWithSensor> page, LightingWithSensor lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getDeviceName()!=null&&!"".equals(lighting.getDeviceName())) {
			wrapper.like("n.deviceName",lighting.getDeviceName());
		}
		return lightingService.getSensorListByLighting(page, wrapper, getUser());
	}
	
	@ApiOperation("根据id获取灯杆的信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(dataType = "String",name = "lightingid", value = "灯杆Id", paramType = "query", required = true)
		,@ApiImplicitParam(dataType = "String",name = "user-holder", value = "请求头", paramType = "header", required = true)
	})
	@GetMapping("/getLightingListById")
	public  List<LightingWithOthers> getLightingListById(Lighting lighting){
		QueryWrapper<Lighting> wrapper = Wrappers.<Lighting>query();
		if (lighting.getAdscreenid()!=null&&!lighting.getAdscreenid().equals("")) {
			wrapper.eq("l.ADSCREENID",lighting.getAdscreenid());
		}
		if (lighting.getLampsid()!=null&&!lighting.getLampsid().equals("")) {
			wrapper.eq("l.LAMPSID",lighting.getLampsid());
		}
		if (lighting.getCameraid()!=null&&!lighting.getCameraid().equals("")) {
			wrapper.eq("l.CAMERAID",lighting.getCameraid());
		}
		if (lighting.getAlarmboxid()!=null&&!lighting.getAlarmboxid().equals("")) {
			wrapper.eq("l.ALARMBOXID",lighting.getAlarmboxid());
		}
		if (lighting.getSensorid()!=null&&!lighting.getSensorid().equals("")) {
			wrapper.eq("l.SENSORID",lighting.getSensorid());
		}
		if (lighting.getWeatherid()!=null&&!lighting.getWeatherid().equals("")) {
			wrapper.eq("l.WEATHERID",lighting.getWeatherid());
		}
		if (lighting.getAreaid()!=null&&!lighting.getAreaid().equals("")) {
			wrapper.eq("l.AREAID",lighting.getAreaid());
		}
		if (lighting.getProjectid()!=null&&!lighting.getProjectid().equals("")) {
			wrapper.eq("l.PROJECTID",lighting.getProjectid());
		}
		if (lighting.getLightingid()!=null&&!lighting.getLightingid().equals("")) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		wrapper.eq("l.isdeleted",0);
		return lightingService.getLightingListById(wrapper,getUser());
	}
	
	@ApiOperation("新增灯杆的信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(dataType = "String",name = "lightmodel", value = "灯杆型号", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "lightingname", value = "灯杆名称", paramType = "query", required = true)
		,@ApiImplicitParam(dataType = "String",name = "areaid", value = "区域ID", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "projectid", value = "项目Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "remark", value = "备注", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "Double",name = "longitude", value = "经度", paramType = "query", required =true)
		,@ApiImplicitParam(dataType = "Double",name = "latitude", value = "纬度", paramType = "query", required = true)
	})
	@LogProcessor
	@PostMapping("/addLighting")
	public ResultWrapper addLighting(Lighting lighting) {
		if (lighting.getLightingname()==null||lighting.getLightingname().equals("")) {
			return ResultWrapper.success(false).message("灯杆名称不能为空");
		}
		int count = lightingService.count(
				Wrappers.<Lighting>query().lambda().eq(Lighting::getLightingname, lighting.getLightingname()));
		if (count > 0) {
			return ResultWrapper.success(false).message("灯杆名称已存在");
		}
		return ResultWrapper.success(lightingService.save(lighting)).message("灯杆新增成功");
	}
	
	@ApiOperation("编辑灯杆的信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(dataType = "String",name = "lightingid", value = "灯杆Id", paramType = "query", required = true)
		,@ApiImplicitParam(dataType = "String",name = "lightingname", value = "灯杆名称", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "areaid", value = "区域ID", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "projectid", value = "项目Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "remark", value = "备注", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "Double",name = "longitude", value = "经度", paramType = "query", required =false)
		,@ApiImplicitParam(dataType = "Double",name = "latitude", value = "纬度", paramType = "query", required = false)
	})
	@LogProcessor
	@PostMapping("/updateLighting")
	public ResultWrapper updateLighting(Lighting lighting) {
		lighting.setIsdeleted(0);
		if (lighting.getLightingid()==null||lighting.getLightingid().equals("")) {
			return ResultWrapper.success(false).message("灯杆id不能为空");
		}
		if (lighting.getLightingname()!=null&&!lighting.getLightingname().equals("")) {
			int count = lightingService.count(
					Wrappers.<Lighting>query().lambda().eq(Lighting::getLightingname, lighting.getLightingname()).eq(Lighting::getIsdeleted,lighting.getIsdeleted()));
			if (count > 1) {
				return ResultWrapper.success(false).message("灯杆名称已存在");
			}
		}
		if (lightingService.updateById(lighting)) {
			return ResultWrapper.success(lightingService.updateById(lighting)).message("灯杆修改成功");
		}
		return ResultWrapper.success(lightingService.updateById(lighting)).message("灯杆修改失败");
	}

	@ApiOperation("批量删除灯杆")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "lightingIds[]", value = "灯杆Id数组", paramType = "query", allowMultiple = true,required = true)
	})
	@LogProcessor
	@PostMapping("/delLighting")
	public ResultWrapper delLighting(@RequestParam("lightingIds[]")String[] lightingIds) {
		if (lightingIds.length==0) {
			return ResultWrapper.success(false).message("灯杆id不能为空");
		}
		return lightingService.delLightingByIsdel(Arrays.asList(lightingIds)).message("灯杆删除成功");
	}
	
	@ApiOperation("设备绑定")
	@ApiImplicitParams({ 
		@ApiImplicitParam(dataType = "String",name = "lightingid", value = "灯杆Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "lampsid", value = "灯具Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "adscreenid", value = "广告屏Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "cameraid", value = "摄像头Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "alarmboxid", value = "一键报警盒Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "sensorid", value = "传感器Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "weatherid", value = "气象Id", paramType = "query", required = false)
	})
	@LogProcessor
	@PostMapping("deviceBinding")
//	@Transactional
	public  ResultWrapper deviceBinding(Lighting lighting) {
		QueryWrapper<Object> wrapper = Wrappers.<Object>query();
		if (lighting.getLightingid()==null||lighting.getLightingid().equals("")) {
			return ResultWrapper.success(false).message("灯杆id不能为空");
		}
//		Boolean bool = lightingService.updateById(lighting);
//		Boolean devicebool=false;
//		if (!bool) {
//			throw new RuntimeException();
//		}
//		else {
//			if (lighting.getAdscreenid()!=null&&!lighting.getAdscreenid().equals("")) {
//				devicebool=lightingService.adscreenBind(lighting.getAdscreenid(), wrapper,lighting.getLightingid());
//				if (!devicebool) {
//					throw new RuntimeException();
//				}
//			}
//			if (lighting.getAlarmboxid()!=null&&!"".equals(lighting.getAlarmboxid())) {
//				devicebool=lightingService.alarmboxBind(lighting.getAlarmboxid(), wrapper,lighting.getLightingid());
//				if (!devicebool) {
//					throw new RuntimeException();
//				}
//			}
//			if (lighting.getCameraid()!=null&&!lighting.getCameraid().equals("")) {
//				devicebool=lightingService.cameraBind(lighting.getCameraid(), wrapper,lighting.getLightingid());
//				if (!devicebool) {
//					throw new RuntimeException();
//				}
//			}
//			if (lighting.getLampsid()!=null&&!lighting.getLampsid().equals("")) {
//				devicebool=lightingService.brightBind(lighting.getLampsid(), wrapper,lighting.getLightingid());
//				if (!devicebool) {
//					throw new RuntimeException();
//				}
//			}
//			if (lighting.getSensorid()!=null&&!lighting.getSensorid().equals("")) {
//				devicebool=lightingService.envirBind(lighting.getSensorid(), wrapper,lighting.getLightingid());
//				if (!devicebool) {
//					throw new RuntimeException();
//				}
//			}
//		}
		if (lightingService.updateById(lighting)) {
			return ResultWrapper.success(lightingService.updateById(lighting)).message("灯杆绑定成功");
		}
		return ResultWrapper.success(lightingService.updateById(lighting)).message("灯杆绑定失败");
	}
	
	@ApiOperation("设备解绑")
	@ApiImplicitParams({ 
		@ApiImplicitParam(dataType = "String",name = "lightingid", value = "灯杆Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "lampsid", value = "灯具Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "adscreenid", value = "广告屏Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "cameraid", value = "摄像头Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "alarmboxid", value = "一键报警盒Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "sensorid", value = "传感器Id", paramType = "query", required = false)
		,@ApiImplicitParam(dataType = "String",name = "weatherid", value = "气象Id", paramType = "query", required = false)
	})
	@LogProcessor
	@PostMapping("deviceLoseBinding")
//	@Transactional
	public  ResultWrapper deviceLoseBinding(Lighting lighting) {
		QueryWrapper<Object> wrapper = Wrappers.<Object>query();
		QueryWrapper<Object> wrapper1 = Wrappers.<Object>query();
		if (lighting.getLightingid()==null||lighting.getLightingid().equals("")) {
			return ResultWrapper.success(false).message("灯杆id不能为空");
		}
		int flag=0;
		if (lighting.getAdscreenid()!=null&&!lighting.getAdscreenid().equals("")) {
			Boolean bool = lightingService.lightingLoseAdscreen(wrapper,lighting.getLightingid());
			if (bool) {
				flag++;
			}
//			Boolean devicebool=false;
//			if (!bool) {
//				throw new RuntimeException();
//			}
//			else {
//				devicebool=lightingService.adscreenLoseBind(wrapper1,lighting.getAdscreenid());
//				if (!devicebool) {
//					throw new RuntimeException();
//				}
//				else {
//					flag++;
//				}
//			}
		}
		if (lighting.getAlarmboxid()!=null&&!lighting.getAlarmboxid().equals("")) {
			Boolean bool = lightingService.lightingLoseAlarmbox(wrapper,lighting.getLightingid());
			if (bool) {
				flag++;
			}
//			Boolean devicebool=false;
//			if (!bool) {
//				throw new RuntimeException();
//			}
//			else {
//				devicebool=lightingService.alarmboxLoseBind(wrapper1,lighting.getAlarmboxid());
//				if (!devicebool) {
//					throw new RuntimeException();
//				}
//				else {
//					flag++;
//				}
//			}
		}
		if (lighting.getCameraid()!=null&&!lighting.getCameraid().equals("")) {
			Boolean bool = lightingService.lightingLoseCamera(wrapper,lighting.getLightingid());
			if (bool) {
				flag++;
			}
//			Boolean devicebool=false;
//			if (!bool) {
//				throw new RuntimeException();
//			}
//			else {
//				devicebool=lightingService.cameraLoseBind(wrapper1,lighting.getCameraid());
//				if (!devicebool) {
//					throw new RuntimeException();
//				}
//				else {
//					flag++;
//				}
//			}
		}
		if (lighting.getLampsid()!=null&&!lighting.getLampsid().equals("")) {
			Boolean bool = lightingService.lightingLoseBright(wrapper,lighting.getLightingid());
			if (bool) {
				flag++;
			}
//			Boolean devicebool=false;
//			if (!bool) {
//				throw new RuntimeException();
//			}
//			else {
//				devicebool=lightingService.brightLoseBind(wrapper1,lighting.getLampsid());
//				if (!devicebool) {
//					throw new RuntimeException();
//				}
//				else {
//					flag++;
//				}
//			}
		}
		if (lighting.getSensorid()!=null&&!lighting.getSensorid().equals("")) {
			Boolean bool = lightingService.lightingLoseSensor(wrapper,lighting.getLightingid());
			if (bool) {
				flag++;
			}
//			Boolean devicebool=false;
//			if (!bool) {
//				throw new RuntimeException();
//			}
//			else {
//				devicebool=lightingService.sensorLoseBind(wrapper1,lighting.getSensorid());
//				if (!devicebool) {
//					throw new RuntimeException();
//				}
//				else {
//					flag++;
//				}
//			}
		}
		if (flag>0) {
			return ResultWrapper.success().message("灯杆解绑成功");
		}
		return ResultWrapper.success().message("灯杆解绑失败");
	}
	@GetMapping("/adscreenNotBind")
	public  IPage<LightingWithAds>  adscreenNotBind(Page<LightingWithAds> page) {
		return lightingService.getAdscreenNotBind(page, getUser());
	}
	@GetMapping("/cameraNotBind")
	public  IPage<LightingWithCamera>  cameraNotBind(Page<LightingWithCamera> page) {
		return lightingService.getCameraNotBind(page, getUser());
	}
	@GetMapping("/alarmNotBind")
	public  IPage<LightingWithAlarm>  alarmNotBind(Page<LightingWithAlarm> page) {
		return lightingService.getAlarmNotBind(page,getUser());
	}
	@GetMapping("/sensorNotBind")
	public  IPage<LightingWithSensor>  sensorNotBind(Page<LightingWithSensor> page) {
		return lightingService.getSensorNotBind(page, getUser());
	}
	@GetMapping("/brightNotBind")
	public  IPage<LightingWithLamps>  brightNotBind(Page<LightingWithLamps> page) {
		return lightingService.getBrightNotBind(page, getUser());
	}
	
	@GetMapping("/getLightingByIds")
	public List<Map<String,Object>> getLightingByIds(String[] deviceIds,String projectid,String areaid) {
		Lighting lighting = new Lighting();
		lighting.setProjectid(projectid);
		lighting.setAreaid(areaid);
		return lightingService.getLightingByIds(Arrays.asList(deviceIds),lighting);
	}
	
	@GetMapping("/getLighting")
	public Map<String,Object> getLighting(Lighting lighting){
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
		wrapper.eq("isdeleted",0);
		return lightingService.getLighting(lighting, wrapper);
	}
	
	@GetMapping("/getCountByAreaId")
	public ResultWrapper getCountByAreaId(Lighting lighting) {
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
		wrapper.eq("areaid",lighting.getAreaid());
		wrapper.eq("isdeleted",0);
		return lightingService.getCountByAreaId(wrapper, getUser());
	}
	
	@GetMapping("/getLightingAllList")
	public ResultWrapper getLightingAllList() {
		return lightingService.getLightingListAll(UserHolder.getUser());
	}
	
	//根据时间段获取区域下灯杆总能耗
	@GetMapping("/getDailyLightingPowerByDate")
	public ResultWrapper getDailyLightingPowerByDate(String start,String end,String projectId,String areaId) {
		return ResultWrapper.success().object(sensorFeign.getSensorStateListByIds(start,end,lightingService.getSensorIdsByLighting(areaId, projectId)));
	}
	
	//根据时间周期获取所有灯杆总能耗以及每个灯杆总能耗以及每个灯杆上每天的总能耗
	@GetMapping("/getDailyDevicePowerByLighting")
	public ResultWrapper getDailyDevicePowerByLighting(String start,String end,String projectId,String areaId) {
		return ResultWrapper.success().object(sensorFeign.getJsonDailyDevicePowerByDateAndDeviceIds(start, end, lightingService.getSensorsByLighting(areaId, projectId)));
	}
	
	//根据时间段获取每个灯杆的总能耗
	@GetMapping("/getJsonDailyDevicePowerByLighting")
	public ResultWrapper getJsonDailyDevicePowerByLighting(String start,String end,String projectId,String areaId) {
		return ResultWrapper.success().object(sensorFeign.getJsonDailyDevicePowerByLighting(start, end, lightingService.getSensorIdsByLighting(areaId, projectId)));
	}
	
	//获取灯杆时间段内每天的总能耗柱状图
	@GetMapping("/getDailyDetailByLighting")
	public ResultWrapper getDailyDetailByLighting(String start,String end,String projectId,String areaId,String lightingid) {
		return ResultWrapper.success().object(sensorFeign.getSensorStateListById(start, end, lightingService.getSensorIdByLighting(areaId, projectId,lightingid)));
	}
	
	//根据灯杆id获取时间段内每天的信息折线图
	@GetMapping("/getJsonDailyPowerStateLog")
	public ResultWrapper getJsonDailyPowerStateLog(String start,String end,String projectId,String areaId,String lightingid) {
		return ResultWrapper.success().object(sensorFeign.getJsonDailyPowerStateLog(start, end, lightingService.getSensorIdByLighting(areaId, projectId,lightingid)));
	}
}

