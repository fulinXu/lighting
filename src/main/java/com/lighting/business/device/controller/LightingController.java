package com.lighting.business.device.controller;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
//import com.lighting.business.config.RabbitConfig;
import com.lighting.business.device.entity.*;
import io.swagger.models.auth.In;
import landsky.basic.feign.adscreen.AdScreenFeignService;
import landsky.basic.feign.alarm.AlarmFeignService;
import landsky.basic.feign.bright.BrightFeignService;
import landsky.basic.feign.camera.CameraFeignService;
import landsky.basic.feign.envir.EnvirFeignService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lighting.business.device.service.ILightingService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import landsky.basic.common.ResultWrapper;
import landsky.basic.entity.UserHolder;
import landsky.basic.annotation.LogProcessor;
import landsky.basic.common.BaseController;

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

//	@Autowired
//	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private EnvirFeignService sensorFeign;

	@Autowired
	private AdScreenFeignService adScreenFeignService;

	@Autowired
	private BrightFeignService brightFeignService;

	@Autowired
	private CameraFeignService cameraFeignService;

	@Autowired
	private AlarmFeignService alarmFeignService;
//
//	@Autowired
//    private AmqpTemplate amqpTemplate;
//
//	@Autowired
//	private RabbitTemplate rabbitTemplate;

	private final String INDEX = "smartcity3307";
	private final String TYPE = "t_lighting";
	
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
//
//	@PostMapping("/getLightingListByES")
//	public String getLightingListByES() throws  Exception{
//        // 构造搜索条件
//        QueryBuilder builder=QueryBuilders.existsQuery("lightingid");
//        SearchQuery searchQuery=new NativeSearchQueryBuilder().withQuery(builder).build();
//        // 执行查询
//        List<Lighting> lightings=elasticsearchTemplate.queryForList(searchQuery,Lighting.class);
//        for(Lighting lighting:lightings){
//            System.out.println(lighting);
//        }
//        return "Search Success";
//
//	}
	
	@ApiOperation("通过灯杆获取灯具的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getLampsListByLighting")
	public  IPage<LightingWithLamps> getLampsListByLighting(Page<LightingWithLamps> page, LightingWithLamps lighting){
		QueryWrapper<LightingWithLamps> wrapper = Wrappers.<LightingWithLamps>query();
        if (lighting.getAlias()!=null&&!"".equals(lighting.getAlias())) {
            wrapper.like("n.alias",lighting.getAlias());
        }
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.projectid",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.areaid",lighting.getAreaid());
		}
		return lightingService.getLampsListByLighting(page, wrapper,getUser());
	}

	@ApiOperation("通过灯杆获取广告屏的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getAdsListByLighting")
	public  IPage<LightingWithAds> getAdsListByLighting(Page<LightingWithAds> page, LightingWithAds lighting){
		QueryWrapper<LightingWithAds> wrapper = Wrappers.<LightingWithAds>query();
        if (lighting.getDeviceName()!=null&&!"".equals(lighting.getDeviceName())) {
            wrapper.like("n.device_name",lighting.getDeviceName());
        }
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getAdsListByLighting(page, wrapper, getUser());
	}

	@ApiOperation("通过灯杆获取摄像头的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getCameraListByLighting")
	public  IPage<LightingWithCamera> getCameraListByLighting(Page<LightingWithCamera> page, LightingWithCamera lighting){
		QueryWrapper<LightingWithCamera> wrapper = Wrappers.<LightingWithCamera>query();
        if (lighting.getDeviceAlias()!=null&&!"".equals(lighting.getDeviceAlias())) {
            wrapper.like("n.device_alias",lighting.getDeviceAlias());
        }
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getCameraListByLighting(page, wrapper, getUser());
	}
	
	@ApiOperation("通过灯杆获取报警盒的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getAlarmListByLighting")
	public  IPage<LightingWithAlarm> getAlarmListByLighting(Page<LightingWithAlarm> page, LightingWithAlarm lighting){
		QueryWrapper<LightingWithAlarm> wrapper = Wrappers.<LightingWithAlarm>query();
        if (lighting.getDeviceAlias()!=null&&!"".equals(lighting.getDeviceAlias())) {
            wrapper.like("n.device_alias",lighting.getDeviceAlias());
        }
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getAlarmListByLighting(page, wrapper, getUser());
}


	@ApiOperation("通过灯杆获取传感器的信息")
	@ApiImplicitParams({
	})
	@GetMapping("/getSensorListByLighting")
	public  IPage<LightingWithSensor> getSensorListByLighting(Page<LightingWithSensor> page, LightingWithSensor lighting){
		QueryWrapper<LightingWithSensor> wrapper = Wrappers.<LightingWithSensor>query();
        if (lighting.getDeviceName()!=null&&!"".equals(lighting.getDeviceName())) {
            wrapper.like("n.deviceName",lighting.getDeviceName());
        }
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getSensorListByLighting(page, wrapper, getUser());
	}

	@ApiOperation("通过灯杆获取充电桩的信息")
	@ApiImplicitParams({
	})
	@GetMapping("/getEvseListByLighting")
	public  IPage<LightingWithEvse> getEvseListByLighting(Page<LightingWithEvse> page, LightingWithEvse lighting){
		QueryWrapper<LightingWithEvse> wrapper = Wrappers.<LightingWithEvse>query();
        if (lighting.getDZMC() != null && !"".equals(lighting.getDZMC())) {
            wrapper.like("n.DZMC", lighting.getDZMC());
        }
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getEvseListByLighting(page, wrapper, getUser());
	}

	@ApiOperation("通过灯杆获取气象站的信息")
	@ApiImplicitParams({
	})
	@GetMapping("/getWeatherListByLighting")
	public  IPage<LightingWithSensor> getWeatherListByLighting(Page<LightingWithSensor> page, LightingWithSensor lighting){
		QueryWrapper<LightingWithSensor> wrapper = Wrappers.<LightingWithSensor>query();
        if (lighting.getDeviceName()!=null&&!"".equals(lighting.getDeviceName())) {
            wrapper.like("n.deviceName",lighting.getDeviceName());
        }
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getWeatherListByLighting(page, wrapper, getUser());
	}

	@ApiOperation("通过灯杆获取积水传感器的信息")
	@ApiImplicitParams({
	})
	@GetMapping("/getWaterListByLighting")
	public  IPage<LightingWithSensor> getWaterListByLighting(Page<LightingWithSensor> page, LightingWithSensor lighting){
		QueryWrapper<LightingWithSensor> wrapper = Wrappers.<LightingWithSensor>query();
        if (lighting.getDeviceName()!=null&&!"".equals(lighting.getDeviceName())) {
            wrapper.like("n.deviceName",lighting.getDeviceName());
        }
		if (lighting.getLightingid()!=null&&!"".equals(lighting.getLightingid())) {
			wrapper.eq("l.lightingid",lighting.getLightingid());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getWaterListByLighting(page, wrapper, getUser());
	}

	@ApiOperation("通过模糊查询灯杆获取灯具的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getLampsListByLightingLike")
	public  IPage<LightingWithLamps> getLampsListByLightingLike(Page<LightingWithLamps> page, LightingWithLamps lighting){
		QueryWrapper<LightingWithLamps> wrapper = Wrappers.<LightingWithLamps>query();
		if (lighting.getAlias()!=null&&!"".equals(lighting.getAlias())) {
			wrapper.like("n.alias",lighting.getAlias());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.projectid",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.areaid",lighting.getAreaid());
		}
		return lightingService.getLampsListByLighting(page, wrapper,getUser());
	}
	
	@ApiOperation("通过模糊查询灯杆获取广告屏的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getAdsListByLightingLike")
	public  IPage<LightingWithAds> getAdsListByLightingLike(Page<LightingWithAds> page, LightingWithAds lighting){
		QueryWrapper<LightingWithAds> wrapper = Wrappers.<LightingWithAds>query();
		if (lighting.getDeviceName()!=null&&!"".equals(lighting.getDeviceName())) {
			wrapper.like("n.device_name",lighting.getDeviceName());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getAdsListByLighting(page, wrapper, getUser());
	}
	
	@ApiOperation("通过模糊查询灯杆获取摄像头的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getCameraListByLightingLike")
	public  IPage<LightingWithCamera> getCameraListByLightingLike(Page<LightingWithCamera> page, LightingWithCamera lighting){
		QueryWrapper<LightingWithCamera> wrapper = Wrappers.<LightingWithCamera>query();
		if (lighting.getDeviceAlias()!=null&&!"".equals(lighting.getDeviceAlias())) {
			wrapper.like("n.device_alias",lighting.getDeviceAlias());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getCameraListByLighting(page, wrapper, getUser());
	}
	
	@ApiOperation("通过模糊查询灯杆获取报警盒的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getAlarmListByLightingLike")
	public  IPage<LightingWithAlarm> getAlarmListByLightingLike(Page<LightingWithAlarm> page, LightingWithAlarm lighting){
		QueryWrapper<LightingWithAlarm> wrapper = Wrappers.<LightingWithAlarm>query();
		if (lighting.getDeviceAlias()!=null&&!"".equals(lighting.getDeviceAlias())) {
			wrapper.like("n.device_alias",lighting.getDeviceAlias());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getAlarmListByLighting(page, wrapper, getUser());
}


	@ApiOperation("通过模糊查询灯杆获取传感器的信息")
	@ApiImplicitParams({ 
	})
	@GetMapping("/getSensorListByLightingLike")
	public  IPage<LightingWithSensor> getSensorListByLightingLike(Page<LightingWithSensor> page, LightingWithSensor lighting){
		QueryWrapper<LightingWithSensor> wrapper = Wrappers.<LightingWithSensor>query();
		if (lighting.getDeviceName()!=null&&!"".equals(lighting.getDeviceName())) {
			wrapper.like("n.deviceName",lighting.getDeviceName());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getSensorListByLighting(page, wrapper, getUser());
	}


	@ApiOperation("通过灯杆模糊获取充电桩的信息")
	@ApiImplicitParams({
	})
	@GetMapping("/getEvseListByLightingLike")
	public  IPage<LightingWithEvse> getEvseListByLightingLike(Page<LightingWithEvse> page, LightingWithEvse lighting) {
		QueryWrapper<LightingWithEvse> wrapper = Wrappers.<LightingWithEvse>query();
		if (lighting.getDZMC() != null && !"".equals(lighting.getDZMC())) {
			wrapper.like("n.DZMC", lighting.getDZMC());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getEvseListByLighting(page, wrapper, getUser());
	}

	@ApiOperation("通过灯杆模糊获取气象站的信息")
	@ApiImplicitParams({
	})
	@GetMapping("/getWeatherListByLightingLike")
	public  IPage<LightingWithSensor> getWeatherListByLightingLike(Page<LightingWithSensor> page, LightingWithSensor lighting){
		QueryWrapper<LightingWithSensor> wrapper = Wrappers.<LightingWithSensor>query();
		if (lighting.getDeviceName()!=null&&!"".equals(lighting.getDeviceName())) {
			wrapper.like("n.deviceName",lighting.getDeviceName());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getWeatherListByLighting(page, wrapper, getUser());
	}

	@ApiOperation("通过灯杆模糊获取积水传感器的信息")
	@ApiImplicitParams({
	})
	@GetMapping("/getWaterListByLightingLike")
	public  IPage<LightingWithSensor> getWaterListByLightingLike(Page<LightingWithSensor> page, LightingWithSensor lighting){
		QueryWrapper<LightingWithSensor> wrapper = Wrappers.<LightingWithSensor>query();
		if (lighting.getDeviceName()!=null&&!"".equals(lighting.getDeviceName())) {
			wrapper.like("n.deviceName",lighting.getDeviceName());
		}
		if (lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())){
			wrapper.eq("n.project_id",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())){
			wrapper.eq("n.area_id",lighting.getAreaid());
		}
		return lightingService.getWaterListByLighting(page, wrapper, getUser());
	}


	/**
	 * 根据设备id获取故障坐标
	 */
	@GetMapping("/getCoordinateByDevice")
	public  Map<String,Double> getCoordinateByDevice(String deviceId,Integer deviceType){
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
		if (deviceType==8){
			wrapper.eq("sensorid",deviceId);
		}
		if (deviceType==4){
			wrapper.eq("alarmboxid",deviceId);
		}
		wrapper.eq("isdeleted",0);
		return  lightingService.getCoordinateByDevice(wrapper);
	}

    /**
     * 获取所有设备数量
     */
    @GetMapping("/getAllDeviceNumberList")
    public  ResultWrapper getAllDeviceNumberList(String projectid,String areaid){
        return  ResultWrapper.success().object(lightingService.getAllDeviceNumberList(projectid,areaid));
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
				Wrappers.<Lighting>query().lambda().eq(Lighting::getIsdeleted,0).eq(Lighting::getLightingname, lighting.getLightingname()));
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

	//获取项目区域下的工控机id
	@GetMapping("/getIPCIds")
	public ResultWrapper getIPCIds(Lighting lighting){
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
		wrapper.eq("isdeleted",0);
		wrapper.eq("projectid",lighting.getProjectid());
		return  ResultWrapper.success().object(lightingService.getIPCIds(wrapper));
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

	//是否包含已绑定的设备

	/**
	 * 绑定了不让修改返回true
	 * @param deviceIds
	 * @param projectId
	 * @param areaId
	 * @return
	 */
	@GetMapping("/isBinding")
	public  Boolean isBinding(String[] deviceIds,String projectId,String areaId){
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
		if (projectId!=null&&!"".equals(projectId)){
			if (areaId!=null&&!"".equals(areaId)){

				return  lightingService.isEqArea(deviceIds,projectId,areaId);
			}
			return  true;
		}
		wrapper.or().in("lampsid",Arrays.asList(deviceIds));
		wrapper.or().in("adscreenid",Arrays.asList(deviceIds));
		wrapper.or().in("cameraid",Arrays.asList(deviceIds));
		wrapper.or().in("alarmboxid",Arrays.asList(deviceIds));
		wrapper.or().in("sensorid",Arrays.asList(deviceIds));
		return  lightingService.isBinding(wrapper);
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
	public  ResultWrapper deviceBinding(Lighting lighting) {
		QueryWrapper<Object> wrapper = Wrappers.<Object>query();
		if (lighting.getLightingid()==null||lighting.getLightingid().equals("")) {
			return ResultWrapper.success(false).message("灯杆id不能为空");
		}
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
	public  IPage<LightingWithAds>  adscreenNotBind(Page<LightingWithAds> page,Lighting lighting) {
		return lightingService.getAdscreenNotBind(page, getUser(),lighting);
	}
	@GetMapping("/cameraNotBind")
	public  IPage<LightingWithCamera>  cameraNotBind(Page<LightingWithCamera> page,Lighting lighting) {
		return lightingService.getCameraNotBind(page, getUser(),lighting);
	}
	@GetMapping("/alarmNotBind")
	public  IPage<LightingWithAlarm>  alarmNotBind(Page<LightingWithAlarm> page,Lighting lighting) {
		return lightingService.getAlarmNotBind(page,getUser(),lighting);
	}
	@GetMapping("/sensorNotBind")
	public  IPage<LightingWithSensor>  sensorNotBind(Page<LightingWithSensor> page,Lighting lighting) {
		return lightingService.getSensorNotBind(page, getUser(),lighting);
	}
	@GetMapping("/brightNotBind")
	public  IPage<LightingWithLamps>  brightNotBind(Page<LightingWithLamps> page,Lighting lighting) {
		return lightingService.getBrightNotBind(page, getUser(),lighting);
	}
	/**
	 * 获取未绑定的音柱信息
	 */
	@GetMapping("/sponNotBind")
	public  IPage<LightingWithSpon>  sponNotBind(Page<LightingWithSpon> page,Lighting lighting){
		return lightingService.getSponNotBind(page,getUser(),lighting);
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
	public ResultWrapper getLightingAllList(String projectId,String areaId) {
		return lightingService.getLightingListAll(UserHolder.getUser(),projectId,areaId);
	}
	
	//根据时间段获取区域下灯杆总能耗
	@GetMapping("/getDailyLightingPowerByDate")
	public ResultWrapper getDailyLightingPowerByDate(String start,String end,String projectId,String areaId) {
		return ResultWrapper.success().object(sensorFeign.getSensorStateListByIds(start,end,lightingService.getSensorIdsByLighting(areaId, projectId)));
	}
	
	//根据时间周期获取所有灯杆总能耗以及每个灯杆总能耗以及每个灯杆上每天的总能耗
	@RequestMapping("/getDailyDevicePowerByLighting")
	public ResultWrapper getDailyDevicePowerByLighting(String start,String end,String projectId,String areaId,int pageIndex,int pageSize) {
		return ResultWrapper.success().object(sensorFeign.getJsonDailyDevicePowerByDateAndDeviceIds(start, end, lightingService.getSensorsByLighting(areaId, projectId,pageIndex,pageSize),pageIndex,pageSize));
	}
	
	//根据时间段获取每个灯杆的总能耗
	@GetMapping("/getJsonDailyDevicePowerByLighting")
	public ResultWrapper getJsonDailyDevicePowerByLighting(String start,String end,String projectId,String areaId,int pageIndex,int pageSize) {
		return ResultWrapper.success().object(sensorFeign.getJsonDailyDevicePowerByLighting(start, end, lightingService.getSensorIdsByLighting(areaId, projectId),pageIndex,pageSize));
	}
	
	//获取灯杆时间段内每天的总能耗柱状图
	@GetMapping("/getDailyDetailByLighting")
	public ResultWrapper getDailyDetailByLighting(String start,String end,String projectId,String areaId,String lightingid) {
		return ResultWrapper.success().object(sensorFeign.getSensorStateListById(start, end, lightingService.getSensorIdByLighting(areaId, projectId,lightingid)));
	}
	
	//根据灯杆id获取时间段内每天的信息折线图
	@RequestMapping("/getJsonDailyPowerStateLog")
	public ResultWrapper getJsonDailyPowerStateLog(String start,String end,String projectId,String areaId,String lightingid,int pageIndex,int pageSize) {
		if (end==null||"".equals(end)){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			end = df.format(new Date());// new Date()为获取当前系统时间
		}
		if (start==null||"".equals(start)){
			//获取当前时间过去7天的日期
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
			Date today = calendar.getTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			start = format.format(today);
		}
		String useSensorId = lightingService.getSensorIdByLighting(areaId, projectId,lightingid);
		if (useSensorId==null){
		    return  ResultWrapper.failure().object("该灯杆未绑定工控机");
        }
		return ResultWrapper.success().object(sensorFeign.getJsonDailyPowerStateLog(start, end, lightingService.getSensorIdByLighting(areaId, projectId,lightingid),pageIndex,pageSize));
	}

	//根据时间周期分页获取所有灯杆总能耗以及每个灯杆总能耗以及每个灯杆上每天的总能耗
	@GetMapping("/getJsonDailyDevicePowerByDateAndDeviceIds")
	public IPage<JSONObject> getJsonDailyDevicePowerByDateAndDeviceIds(IPage<JSONObject> page, String start, String end, String projectId, String areaId, int pageIndex, int pageSize) throws JSONException {
		if (end==null||"".equals(end)){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			end = df.format(new Date());// new Date()为获取当前系统时间
		}
		if (start==null||"".equals(start)){
			//获取当前时间过去7天的日期
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
			Date today = calendar.getTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			start = format.format(today);
		}
		JSONObject data = new JSONObject();
		data.put("data",sensorFeign.getJsonDailyDevicePowerByDateAndDeviceIds(start, end, lightingService.getSensorsByLighting(areaId, projectId,pageIndex,pageSize),pageIndex,pageSize));
		List<JSONObject>  list = new ArrayList<>();
		list.add(data);
		int total = 0;
		total = lightingService.getTotalSensorsByLighting(areaId,projectId);
		page.setRecords(list);
		page.setSize(pageSize);
		page.setCurrent(pageIndex);
		page.setTotal(total);
		int pages = 0;
		if (total%pageSize==0){
			pages = total/pageSize;
		}
		else {
			pages = total/pageSize + 1;
		}
		page.setPages(pages);
		return page;
	}

//	@PostMapping("/sendMsg")
//    public void sendMsg(String content) {
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
//        amqpTemplate.convertAndSend(RabbitConfig.EXCHANGE_A,RabbitConfig.ROUTINGKEY_A, content);
////        rabbitTemplate.setDefaultReceiveQueue(content);
//    }

//	@PostMapping("/rabbit-queue-add")
//	public ResultWrapper addQueue(@RequestParam String name) {
//		rabbitMQ.addQueue(name);
//		return ResultWrapper.success().messageAdd();
//	}

//	@PostMapping("/send")
//	public ResultWrapper send(String name,String o) {
//		rabbitMQ.send(name,o);
//		return ResultWrapper.success().messageAdd();
//	}
//	@GetMapping("/directSend")
//	public void directSend() {
//		String message="direct 发送消息";
//		System.out.println("hhhhhhhhhhhhhhhhhhhhhhh");
//		rabbitTemplate.convertAndSend("DirectExchange","DirectKey",
//				message, new CorrelationData(UUID.randomUUID().toString()));
//	}

	@GetMapping("/allDeviceStates")
	public ResultWrapper allDeviceStates(@RequestParam("projectId") String projectId){
		String orgId = projectId;
		Map<String,Object>  adMap = (Map<String, Object>) adScreenFeignService.getAdScreenDeviceStatusCount(projectId).getObject();
		Map<String,Object>  alarmMap = (Map<String, Object>) alarmFeignService.countByProjectId(orgId).getObject();
		Map<String,Object> cameraMap = (Map<String, Object>) cameraFeignService.countByProjectId(orgId).getObject();
		Map<String,Object> brightMap = (Map<String, Object>) brightFeignService.getRateOfLighting(projectId).getObject();
		Map<String,Object> sensorMap = sensorFeign.getDeviceStatusListForLighting(projectId);
		Map<String,Integer> allDeviceState = new HashMap<>();
		Integer online = (Integer)adMap.get("online")+(Integer)alarmMap.get("onlineNum")+(Integer)cameraMap.get("onlineNum")+(Integer)brightMap.get("online")+(Integer)sensorMap.get("on");
		Integer offline = (Integer)adMap.get("offline")+(Integer) alarmMap.get("offlineNum")+(Integer)cameraMap.get("offlineNum")+(Integer)brightMap.get("offline")+(Integer)sensorMap.get("off");
		Integer faultNum = (Integer)adMap.get("broken")+(Integer)alarmMap.get("faultNum")+(Integer)cameraMap.get("faultNum")+(Integer)brightMap.get("isfault")+(Integer)sensorMap.get("fault");
		Integer total = (Integer)adMap.get("total")+(Integer)alarmMap.get("totalNum")+(Integer)cameraMap.get("totalNum")+(Integer)brightMap.get("total")+(Integer)sensorMap.get("on")+(Integer)sensorMap.get("off");
		allDeviceState.put("online",online);
		allDeviceState.put("offline",offline);
		allDeviceState.put("faultNum",faultNum);
		allDeviceState.put("total",total);
		return  ResultWrapper.success().object(allDeviceState);
	}

}

