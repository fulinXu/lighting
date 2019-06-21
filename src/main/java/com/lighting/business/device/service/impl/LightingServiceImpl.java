package com.lighting.business.device.service.impl;

import com.lighting.business.device.entity.*;
import com.lighting.business.device.mapper.LightingMapper;
import com.lighting.business.device.service.ILightingService;

import landsky.basic.common.ResultWrapper;
import landsky.basic.entity.UserHolder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;

import landsky.basic.feign.envir.EnvirFeignService;
import landsky.basic.feign.project.ProjectFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xu Fulin
 * @since 2019-01-21
 */
@Service
public class LightingServiceImpl extends ServiceImpl<LightingMapper, Lighting> implements ILightingService {

	@Autowired
	private ProjectFeignService projectFeignService;

	@Autowired
	private EnvirFeignService sensorFeign;
	
	@Override
	public IPage<LightingWithOthers> getLightingList(Page<LightingWithOthers> page, QueryWrapper<Lighting> wrapper,UserHolder user) {
		// TODO Auto-generated method stub
		System.out.println("sql:" + wrapper.getCustomSqlSegment());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return page;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return page;
		}
		wrapper.in("l.projectid", projectIds).in("l.areaid", areaIds);
		page.setRecords(baseMapper.getLightingList(page, wrapper));
		return page;
	}
	
	@Override
	public List<LightingWithOthers> getLightingListById(QueryWrapper<Lighting> wrapper,UserHolder user) {
//		// TODO Auto-generated method stub
//		System.out.println("sql:" + wrapper.getCustomSqlSegment());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		wrapper.in("l.projectid", projectIds).in("l.areaid", areaIds);
		return baseMapper.getLightingListById(wrapper);
	}

	@Override
	public IPage<LightingWithLamps> getLampsListByLighting(Page<LightingWithLamps> page, QueryWrapper<Lighting> wrapper,
			UserHolder user) {
		// TODO Auto-generated method stub
		System.out.println("sql:" + wrapper.getCustomSqlSegment());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return page;
		}
		wrapper.in("n.projectid", projectIds);
		wrapper.in("n.isdeleted",0);
		return page.setRecords(baseMapper.getLampsListByLighting(page, wrapper));
	}
	
	@Override
	public IPage<LightingWithAds> getAdsListByLighting(Page<LightingWithAds> page, QueryWrapper<Lighting> wrapper,
			UserHolder user) {
		// TODO Auto-generated method stub
		System.out.println("sql:" + wrapper.getCustomSqlSegment());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return page;
		}
		wrapper.in("n.project_id", projectIds);
		wrapper.in("n.deleted",0);
		return page.setRecords(baseMapper.getAdsListByLighting(page, wrapper));
	}
	
	@Override
	public IPage<LightingWithCamera> getCameraListByLighting(Page<LightingWithCamera> page, QueryWrapper<Lighting> wrapper,
			UserHolder user) {
		// TODO Auto-generated method stub
		System.out.println("sql:" + wrapper.getCustomSqlSegment());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return page;
		}
		wrapper.in("n.project_id", projectIds);
		wrapper.in("n.deleted",0);
		return page.setRecords(baseMapper.getCameraListByLighting(page, wrapper));
	}
	
	@Override
	public IPage<LightingWithAlarm> getAlarmListByLighting(Page<LightingWithAlarm> page, QueryWrapper<Lighting> wrapper,
			UserHolder user) {
		// TODO Auto-generated method stub
		System.out.println("sql:" + wrapper.getCustomSqlSegment());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return page;
		}
		wrapper.in("n.project_id", projectIds);
		wrapper.in("n.deleted",0);
		return page.setRecords(baseMapper.getAlarmListByLighting(page, wrapper));
	}
	
	@Override
	public IPage<LightingWithSensor> getSensorListByLighting(Page<LightingWithSensor> page, QueryWrapper<Lighting> wrapper,
			UserHolder user) {
		// TODO Auto-generated method stub
		System.out.println("sql:" + wrapper.getCustomSqlSegment());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return page;
		}
		wrapper.in("n.project_id", projectIds);
		wrapper.in("n.deleted",0);
		return page.setRecords(baseMapper.getSensorListByLighting(page, wrapper));
	}

	@Override
	public IPage<LightingWithSensor> getWeatherListByLighting(Page<LightingWithSensor> page, QueryWrapper<Lighting> wrapper, UserHolder user) {
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return page;
		}
		List<Integer> typeList = new ArrayList<>();
		typeList.add(1);
		typeList.add(3);
		wrapper.in("n.project_id", projectIds);
		wrapper.in("n.deleted",0);
		wrapper.in("n.device_type",typeList);
		return page.setRecords(baseMapper.getSensorListByLighting(page, wrapper));
	}

	@Override
	public IPage<LightingWithSensor> getWaterListByLighting(Page<LightingWithSensor> page, QueryWrapper<Lighting> wrapper, UserHolder user) {
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return page;
		}
		List<Integer> typeList = new ArrayList<>();
		typeList.add(2);
		typeList.add(3);
		wrapper.in("n.project_id", projectIds);
		wrapper.in("n.deleted",0);
		wrapper.in("n.device_type",typeList);
		return page.setRecords(baseMapper.getSensorListByLighting(page, wrapper));
	}

	@Override
	public IPage<LightingWithEvse> getEvseListByLighting(Page<LightingWithEvse> page, QueryWrapper<Lighting> wrapper,
														   UserHolder user) {
		// TODO Auto-generated method stub
		System.out.println("sql:" + wrapper.getCustomSqlSegment());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return page;
		}
		wrapper.in("n.project_id", projectIds);
		wrapper.in("n.deleted",0);
		return page.setRecords(baseMapper.getEvseListByLighting(page, wrapper));
	}

	@Override
	public boolean adscreenBind(String adscreenId,QueryWrapper<Object> wrapper,String bindid) {
		// TODO Auto-generated method stub
		wrapper.eq("id",adscreenId);
		int flag = baseMapper.adscreenBind(bindid,wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean alarmboxBind(String alarmboxId,QueryWrapper<Object> wrapper,String bindid) {
		// TODO Auto-generated method stub
		wrapper.eq("id",alarmboxId);
		int flag = baseMapper.alarmboxBind(bindid,wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean cameraBind(String cameraid,QueryWrapper<Object> wrapper,String bindid) {
		// TODO Auto-generated method stub
		wrapper.eq("id",cameraid);
		int flag = baseMapper.cameraBind(bindid,wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean brightBind(String brightid,QueryWrapper<Object> wrapper,String bindid) {
		// TODO Auto-generated method stub
		wrapper.eq("node_id",brightid);
		int flag = baseMapper.brightBind(bindid,wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean envirBind(String envirId,QueryWrapper<Object> wrapper,String bindid) {
		// TODO Auto-generated method stub
		wrapper.eq("id",envirId);
		int flag = baseMapper.envirBind(bindid,wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean lightingLoseAdscreen(QueryWrapper<Object> wrapper,String bindid) {
		// TODO Auto-generated method stub
		wrapper.eq("lightingid",bindid);
		int flag = baseMapper.lightingLoseAdscreen(wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean adscreenLoseBind(QueryWrapper<Object> wrapper,String adscreenid) {
		// TODO Auto-generated method stub
		wrapper.eq("id",adscreenid);
		int flag = baseMapper.adscreenLoseBind(wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean lightingLoseAlarmbox(QueryWrapper<Object> wrapper,String bindid) {
		// TODO Auto-generated method stub
		wrapper.eq("lightingid",bindid);
		int flag = baseMapper.lightingLoseAlarmbox(wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean alarmboxLoseBind(QueryWrapper<Object> wrapper,String alarmboxid) {
		// TODO Auto-generated method stub
		wrapper.eq("id",alarmboxid);
		int flag = baseMapper.alarmboxLoseBind(wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean lightingLoseCamera(QueryWrapper<Object> wrapper,String bindid) {
		// TODO Auto-generated method stub
		wrapper.eq("lightingid",bindid);
		int flag = baseMapper.lightingLoseCamera(wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean cameraLoseBind(QueryWrapper<Object> wrapper,String cameraid) {
		// TODO Auto-generated method stub
		wrapper.eq("id",cameraid);
		int flag = baseMapper.cameraLoseBind(wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean lightingLoseBright(QueryWrapper<Object> wrapper,String bindid) {
		// TODO Auto-generated method stub
		wrapper.eq("lightingid",bindid);
		int flag = baseMapper.lightingLoseBright(wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean brightLoseBind(QueryWrapper<Object> wrapper,String brightid) {
		// TODO Auto-generated method stub
		wrapper.eq("node_id",brightid);
		int flag = baseMapper.brightLoseBind(wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean lightingLoseSensor(QueryWrapper<Object> wrapper,String bindid) {
		// TODO Auto-generated method stub
		wrapper.eq("lightingid",bindid);
		int flag = baseMapper.lightingLoseSensor(wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean sensorLoseBind(QueryWrapper<Object> wrapper,String sensorid) {
		// TODO Auto-generated method stub
		wrapper.eq("deviceId",sensorid);
		int flag = baseMapper.sensorLoseBind(wrapper);
		if (flag>0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public IPage<LightingWithLamps> getBrightNotBind(Page<LightingWithLamps> page, UserHolder user,Lighting lighting) {
		// TODO Auto-generated method stub
        QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
        wrapper.eq("n.projectid",lighting.getProjectid());
        wrapper.eq("n.areaid",lighting.getAreaid());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		return page.setRecords(baseMapper.getBrightNotBind(wrapper));
	}

	@Override
	public IPage<LightingWithAds> getAdscreenNotBind(Page<LightingWithAds> page, UserHolder user,Lighting lighting) {
		// TODO Auto-generated method stub
        QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
        wrapper.eq("t_ad_screen_device.project_id",lighting.getProjectid());
        wrapper.eq("t_ad_screen_device.area_id",lighting.getAreaid());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		return page.setRecords(baseMapper.getAdscreenNotBind(wrapper));
	}

	@Override
	public IPage<LightingWithCamera> getCameraNotBind(Page<LightingWithCamera> page, UserHolder user,Lighting lighting) {
		// TODO Auto-generated method stub
        QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
        wrapper.eq("camera.project_id",lighting.getProjectid());
        wrapper.eq("camera.area_id",lighting.getAreaid());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		return page.setRecords(baseMapper.getCameraNotBind(wrapper));
	}

	@Override
	public IPage<LightingWithAlarm> getAlarmNotBind(Page<LightingWithAlarm> page, UserHolder user,Lighting lighting) {
		// TODO Auto-generated method stub
        QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
        wrapper.eq("alarm_box.project_id",lighting.getProjectid());
        wrapper.eq("alarm_box.area_id",lighting.getAreaid());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		return page.setRecords(baseMapper.getAlarmNotBind(wrapper));
	}

	@Override
	public IPage<LightingWithSensor> getSensorNotBind(Page<LightingWithSensor> page, UserHolder user,Lighting lighting) {
		// TODO Auto-generated method stub
        QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
        wrapper.eq("t_envir_devices.project_id",lighting.getProjectid());
        wrapper.eq("t_envir_devices.area_id",lighting.getAreaid());
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		return page.setRecords(baseMapper.getSensorNotBind(wrapper));
	}

	@Override
	public List<Map<String, Object>> getLightingByIds(List<String> lightingIds,Lighting lighting) {
		// TODO Auto-generated method stub
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
		wrapper.in("lightingid",lightingIds);
		wrapper.eq("isdeleted",0);
		if (lighting.getAreaid()!=null&&!lighting.getAreaid().equals("")) {
			wrapper.eq("areaid",lighting.getAreaid());
		}
		if (lighting.getProjectid()!=null&&!lighting.getProjectid().equals("")) {
			wrapper.eq("projectid",lighting.getProjectid());
		}
		return baseMapper.selectMaps(wrapper);
	}

	@Override
	public Map<String, Object> getLighting(Lighting lighting, QueryWrapper<Lighting> wrapper) {
		// TODO Auto-generated method stub
		if(lighting.getProjectid()!=null&&!"".equals(lighting.getProjectid())) {
			wrapper.eq("projectid",lighting.getProjectid());
		}
		if (lighting.getAreaid()!=null&&!"".equals(lighting.getAreaid())) {
			wrapper.eq("areaid",lighting.getAreaid());
		}
		if (lighting.getAlarmboxid()!=null&&!"".equals(lighting.getAlarmboxid())) {
			wrapper.eq("alarmboxid",lighting.getAlarmboxid());
		}
		if(lighting.getCameraid()!=null&&!"".equals(lighting.getCameraid())){
			wrapper.eq("cameraid",lighting.getCameraid());
		}

		return baseMapper.selectMaps(wrapper).get(0);
	}

	@Override
	public ResultWrapper getCountByAreaId(QueryWrapper<Lighting> wrapper,UserHolder user) {
		// TODO Auto-generated method stub
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return ResultWrapper.success().message("请确认已登录");
		}
		wrapper.in("areaid",areaIds);
		return ResultWrapper.success().object(baseMapper.selectCount(wrapper));
	}

	@Override
	public ResultWrapper getLightingListAll(UserHolder user) {
		// TODO Auto-generated method stub
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return ResultWrapper.success().object(Collections.emptyList());
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return ResultWrapper.success().object(Collections.emptyList());
		}
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
		return ResultWrapper.success().object(baseMapper.selectList(wrapper.in("projectid",projectIds).in("areaid",areaIds).eq("isdeleted",0)));
	}

	@Override
	public ResultWrapper delLightingByIsdel(List<String> lightingList) {
		// TODO Auto-generated method stub
		Lighting lighting = new Lighting();
		lighting.setIsdeleted(1);
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
		wrapper.in("lightingid",lightingList);
        lighting.setLampsid(null);
        lighting.setAdscreenid(null);
        lighting.setAlarmboxid(null);
        lighting.setCameraid(null);
        lighting.setSensorid(null);
		return ResultWrapper.success().object(baseMapper.update(lighting,wrapper));
	}

	@Override
	public List<Map<String,String>> getSensorsByLighting(String areaId,String projectId,int pageIndex,int pageSize){
		// TODO Auto-generated method stub
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
        if (areaId!=null&&!"".equals(areaId)){
            wrapper.eq("areaid",areaId);
        }
        if (projectId!=null&&!"".equals(projectId)){
            wrapper.eq("projectId",projectId);
        }
		wrapper.eq("isdeleted",0);
		int current = (pageIndex-1)*pageSize;
		List<Lighting> lightings= (List<Lighting>) baseMapper.selectListPage(wrapper,current,pageSize);
		List<Map<String,String>> sensorBylightings = new ArrayList<>();
		for(Lighting lighting : lightings) {
			Map<String,String> map = new HashMap<>();
            if (lighting.getLightingname()!=null&&!"".equals(lighting.getLightingname())) {
                if (lighting.getSensorid()!=null&&!"".equals(lighting.getSensorid())) {
                    map.put("sensorId",lighting.getSensorid());

                }
                map.put("lightingName",lighting.getLightingname());
                sensorBylightings.add(map);
            }
		}
		return sensorBylightings;
	}

	@Override
	public int getTotalSensorsByLighting(String areaId, String projectId) {
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
        if (areaId!=null&&!"".equals(areaId)){
            wrapper.eq("areaid",areaId);
        }
        if (projectId!=null&&!"".equals(projectId)){
            wrapper.eq("projectId",projectId);
        }
		wrapper.isNotNull("sensorid");
		wrapper.ne("sensorid","");
		wrapper.eq("isdeleted",0);
		int i = baseMapper.selectCount(wrapper);
		return i;
	}

	@Override
	public List<String> getSensorIdsByLighting(String areaId, String projectId) {
		// TODO Auto-generated method stub
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
        if (areaId!=null&&!"".equals(areaId)){
            wrapper.eq("areaid",areaId);
        }
        if (projectId!=null&&!"".equals(projectId)){
            wrapper.eq("projectId",projectId);
        }
		wrapper.eq("isdeleted",0);
		List<Lighting> lightings= baseMapper.selectList(wrapper);
		List<String> sensorIds = new ArrayList<>();
		for(Lighting lighting : lightings) {
			if (lighting.getSensorid()!=null&&!"".equals(lighting.getSensorid())) {
				sensorIds.add(lighting.getSensorid());
			}
		}
		return sensorIds;
	}

	@Override
	public String getSensorIdByLighting(String areaId, String projectId,String lightingid) {
		// TODO Auto-generated method stub
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
		if (areaId!=null&&!"".equals(areaId)){
            wrapper.eq("areaid",areaId);
        }
		if (projectId!=null&&!"".equals(projectId)){
            wrapper.eq("projectId",projectId);
        }
		wrapper.eq("isdeleted",0);
		Lighting lighting = baseMapper.selectById(lightingid);
		if (lighting!=null) {
			return lighting.getSensorid();
		}
		else {
			return null;
		}
	}

    @Override
    public List<Object> getIPCIds(QueryWrapper<Lighting> wrapper) {
	    List<String> IPCIds = new ArrayList<>();
	    List<Lighting> lightings = baseMapper.selectList(wrapper);
	    for (Lighting lighting : lightings){
	        if(lighting.getSensorid()!=null&&!"".equals(lighting.getSensorid())){
                IPCIds.add(lighting.getSensorid());
            }
        }
	    if (IPCIds.size()==0){
	        return  null;
        }
        List<Object> IPCs = sensorFeign.getDeviceListByIds(IPCIds);
        return IPCs;
    }

    @Override
    public Boolean isBinding(QueryWrapper<Lighting> wrapper) {
        int count = baseMapper.selectCount(wrapper);
        if (count>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean isEqArea(String[] deviceIds,String projectid, String areaid) {
        QueryWrapper<Lighting> selectWrapper = new QueryWrapper<>();
        selectWrapper.or().in("lampsid", Arrays.asList(deviceIds));
        selectWrapper.or().in("adscreenid",Arrays.asList(deviceIds));
        selectWrapper.or().in("cameraid",Arrays.asList(deviceIds));
        selectWrapper.or().in("alarmboxid",Arrays.asList(deviceIds));
        selectWrapper.or().in("sensorid",Arrays.asList(deviceIds));
        List<Lighting> lightings = baseMapper.selectList(selectWrapper);
        if (lightings.size()>0){
            for(Lighting lighting: lightings){
                if(projectid.equals(lighting.getProjectid())){
                    if (areaid.equals(lighting.getAreaid())){
                        return  false;
                    }
                    return  true;
                }
            }
        }
        return true;
    }

    @Override
    public Map<String,Double> getCoordinateByDevice(QueryWrapper<Lighting> wrapper) {
	    Lighting lighting = baseMapper.selectOne(wrapper);
	    if (lighting==null){
	    	return  null;
		}
	    Map<String,Double> map = new HashMap<>();
	    map.put("longitude",lighting.getLongitude());
	    map.put("latitude",lighting.getLatitude());
        return map;
    }


}
