package com.lighting.business.device.service.impl;

import com.lighting.business.device.entity.Lighting;
import com.lighting.business.device.entity.LightingWithAds;
import com.lighting.business.device.entity.LightingWithAlarm;
import com.lighting.business.device.entity.LightingWithCamera;
import com.lighting.business.device.entity.LightingWithLamps;
import com.lighting.business.device.entity.LightingWithOthers;
import com.lighting.business.device.entity.LightingWithSensor;
import com.lighting.business.device.mapper.LightingMapper;
import com.lighting.business.device.service.ILightingService;

import landsky.basic.common.ResultWrapper;
import landsky.basic.entity.UserHolder;
import landsky.basic.project.feign.ProjectFeignService;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

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
	public IPage<LightingWithLamps> getBrightNotBind(Page<LightingWithLamps> page, UserHolder user) {
		// TODO Auto-generated method stub
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		return page.setRecords(baseMapper.getBrightNotBind());
	}

	@Override
	public IPage<LightingWithAds> getAdscreenNotBind(Page<LightingWithAds> page, UserHolder user) {
		// TODO Auto-generated method stub
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		return page.setRecords(baseMapper.getAdscreenNotBind());
	}

	@Override
	public IPage<LightingWithCamera> getCameraNotBind(Page<LightingWithCamera> page, UserHolder user) {
		// TODO Auto-generated method stub
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		return page.setRecords(baseMapper.getCameraNotBind());
	}

	@Override
	public IPage<LightingWithAlarm> getAlarmNotBind(Page<LightingWithAlarm> page, UserHolder user) {
		// TODO Auto-generated method stub
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		return page.setRecords(baseMapper.getAlarmNotBind());
	}

	@Override
	public IPage<LightingWithSensor> getSensorNotBind(Page<LightingWithSensor> page, UserHolder user) {
		// TODO Auto-generated method stub
		List<String> projectIds = projectFeignService.getProjectIdsByUserId(user.getId());
		if (projectIds.isEmpty()) {
			return null;
		}
		List<String> areaIds = projectFeignService.getAreaIdsByUserId(user.getId());
		if (areaIds.isEmpty()) {
			return null;
		}
		return page.setRecords(baseMapper.getSensorNotBind());
	}

	@Override
	public List<Map<String, Object>> getLightingByIds(List<String> lightingIds,Lighting lighting) {
		// TODO Auto-generated method stub
		QueryWrapper<Lighting> wrapper = new QueryWrapper<>();
		wrapper.in("lightingid",lightingIds);
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

	
	
}
