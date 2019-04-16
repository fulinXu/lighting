package com.lighting.business.device.mapper;

import com.lighting.business.device.entity.Lighting;
import com.lighting.business.device.entity.LightingWithAds;
import com.lighting.business.device.entity.LightingWithAlarm;
import com.lighting.business.device.entity.LightingWithCamera;
import com.lighting.business.device.entity.LightingWithLamps;
import com.lighting.business.device.entity.LightingWithOthers;
import com.lighting.business.device.entity.LightingWithSensor;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xu Fulin
 * @since 2019-01-21
 */
public interface LightingMapper extends BaseMapper<Lighting> {
	/**
	 * 3.0.7支持，2019.01.01.
	 * 
	 * @param page
	 * @param wrapper
	 * @return
	 */
	@Select("select l.*,a.device_name  as adscreenName,c.device_alias  as  deviceAliasc,b.device_alias as alarmboxName,d.device_name as sensorName,l.LIGHTINGNAME,	n.ON_OFF  as  onOff,n.BRIGHTNESS,n.NODE_ID,n.ALIAS,n.`STATUS` from t_lighting l "
			+ "left join (select * from t_ad_screen_device where deleted = 0) a on l.ADSCREENID=a.id "
			+ "left join (select * from camera where deleted = 0) c on l.CAMERAID=c.id "
			+ "left join (select * from alarm_box where deleted = 0) b on l.ALARMBOXID=b.id "
			+ "left join (select * from t_envir_devices where deleted = 0) d on l.SENSORID=d.device_id " 
			+ "left join  t_bright  n on l.LAMPSID=n.node_id "
			+ "${ew.customSqlSegment}")
	List<LightingWithOthers> getLightingList(Page<LightingWithOthers> page, @Param("ew") Wrapper<Lighting> wrapper);
	
	@Select("select l.*,a.device_name,	d.`status` AS  sensorStatus,c.device_alias  as  device_aliasc,c.state AS camera_state,b.device_alias AS alarmboxName,d.device_name  AS sensorName,d.wind_power,d.temperature,d.humidity,d.device_id,l.LIGHTINGNAME,	n.ON_OFF,n.BRIGHTNESS,n.NODE_ID,n.ALIAS,n.`STATUS`,a.device_name AS adDeviceName,f.water from t_lighting l  "
			+ "left join t_ad_screen_device a on l.ADSCREENID=a.id "
			+ "left join camera c on l.CAMERAID=c.id "
			+ "left join alarm_box b on l.ALARMBOXID=b.id "
			+ "left join (select t_evir_weather_log.wind_power,t_envir_devices.`status`,t_evir_weather_log.temperature,t_evir_weather_log.humidity,t_envir_devices.device_name,t_envir_devices.device_id from t_envir_devices LEFT JOIN t_evir_weather_log on t_evir_weather_log.device_id = t_envir_devices.device_id) d ON l.SENSORID = d.device_id " 
			+ "left join t_bright n on l.LAMPSID=n.node_id "
			+ "LEFT JOIN (select t_envir_hydrops_log.water,t_envir_devices.device_id from t_envir_hydrops_log LEFT JOIN t_envir_devices on t_envir_hydrops_log.device_id = t_envir_devices.device_id) f ON l.SENSORID = f.device_id  "
			+ "${ew.customSqlSegment}")
	List<LightingWithOthers> getLightingListById(@Param("ew") Wrapper<Lighting> wrapper);
	
	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  t_lighting l  RIGHT JOIN t_bright n ON n.NODE_ID = l.LAMPSID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithLamps> getLampsListByLighting(Page<LightingWithLamps> page, @Param("ew") Wrapper<Lighting> wrapper);
	
	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  t_lighting l  RIGHT JOIN t_ad_screen_device  n ON n.id = l.ADSCREENID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithAds> getAdsListByLighting(Page<LightingWithAds> page, @Param("ew") Wrapper<Lighting> wrapper);
	
	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  t_lighting l  RIGHT JOIN camera  n ON n.id = l.CAMERAID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithCamera> getCameraListByLighting(Page<LightingWithCamera> page, @Param("ew") Wrapper<Lighting> wrapper);
	
	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  t_lighting l  RIGHT JOIN alarm_box  n ON n.id = l.ALARMBOXID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithAlarm> getAlarmListByLighting(Page<LightingWithAlarm> page, @Param("ew") Wrapper<Lighting> wrapper);
	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  t_lighting l  RIGHT JOIN ("
			+ "SELECT  d.device_id,d.deleted,d.project_id,d.area_id,d.device_name,d.`status`,w.temperature,w.wind_direction,w.wind_power,w.humidity,w.atmospheric_pressure,w.record_time As weatherrecordtime,h.create_time As waterrecordtime,h.supplier_id As watersupplierid,h.water  "
			+ " FROM t_envir_devices d"
			+ " LEFT JOIN t_evir_weather_log w ON d.device_id = w.device_id "
			+ " LEFT JOIN t_envir_hydrops_log h ON d.device_id = h.device_id"
			+ ")  n ON n.device_id = l.SENSORID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithSensor> getSensorListByLighting(Page<LightingWithSensor> page, @Param("ew") Wrapper<Lighting> wrapper);
	
	@Update("update t_ad_screen_device set bind_id = #{bindId}  " 
			+"${ew.customSqlSegment}")
	int adscreenBind(@Param("bindId") String bindId, @Param("ew") Wrapper<Object> wrapper);
	
	@Update("update alarm_box set bind_id = #{bindId}  " 
			+"${ew.customSqlSegment}")
	int alarmboxBind(@Param("bindId") String bindId, @Param("ew") Wrapper<Object> wrapper);
	
	@Update("update camera set bind_id = #{bindId}  " 
			+"${ew.customSqlSegment}")
	int cameraBind(@Param("bindId") String bindId, @Param("ew") Wrapper<Object> wrapper);
	
	@Update("update t_bright set bind_id = #{bindId}  " 
			+"${ew.customSqlSegment}")
	int brightBind(@Param("bindId") String bindId, @Param("ew") Wrapper<Object> wrapper);
	
	@Update("update t_envir_devices set bind_id = #{bindId}  " 
			+"${ew.customSqlSegment}")
	int envirBind(@Param("bindId") String bindId, @Param("ew") Wrapper<Object> wrapper);
	
	@Update("update t_lighting set adscreenid = null  " 
			+"${ew.customSqlSegment}")
	int lightingLoseAdscreen(@Param("ew") Wrapper<Object> wrapper);
	
	@Update("update t_ad_screen_device set bind_id = null  " 
			+"${ew.customSqlSegment}")
	int adscreenLoseBind(@Param("ew") Wrapper<Object> wrapper);
	
	@Update("update t_lighting set ALARMBOXID = null  " 
			+"${ew.customSqlSegment}")
	int lightingLoseAlarmbox(@Param("ew") Wrapper<Object> wrapper);
	
	@Update("update alarm_box set bind_id = null  " 
			+"${ew.customSqlSegment}")
	int alarmboxLoseBind(@Param("ew") Wrapper<Object> wrapper);
	
	@Update("update t_lighting set  CAMERAID = null  " 
			+"${ew.customSqlSegment}")
	int lightingLoseCamera(@Param("ew") Wrapper<Object> wrapper);
	
	@Update("update camera set bind_id = null  " 
			+"${ew.customSqlSegment}")
	int cameraLoseBind(@Param("ew") Wrapper<Object> wrapper);
	
	@Update("update t_lighting set  lampsid = null  " 
			+"${ew.customSqlSegment}")
	int lightingLoseBright(@Param("ew") Wrapper<Object> wrapper);
	
	@Update("update t_bright set bind_id = null  " 
			+"${ew.customSqlSegment}")
	int brightLoseBind(@Param("ew") Wrapper<Object> wrapper);
	
	@Update("update t_lighting set  sensorid = null  " 
			+"${ew.customSqlSegment}")
	int lightingLoseSensor(@Param("ew") Wrapper<Object> wrapper);
	
	@Update("update t_envir_devices set bind_id = null  " 
			+"${ew.customSqlSegment}")
	int sensorLoseBind(@Param("ew") Wrapper<Object> wrapper);
	
	@Select("SELECT  *  FROM 	t_lighting right JOIN t_ad_screen_device ON t_ad_screen_device.id = t_lighting.ADSCREENID where LIGHTINGID is NULL  and deleted = '0' OR LIGHTINGID='' ")
	List<LightingWithAds> getAdscreenNotBind();
	
	@Select("SELECT  *  FROM 	t_lighting right JOIN t_envir_devices ON t_envir_devices.device_id = t_lighting.SENSORID where LIGHTINGID is NULL and  deleted = '0' OR LIGHTINGID=''")
	List<LightingWithSensor> getSensorNotBind();
	
	@Select("SELECT  *  FROM 	t_lighting right JOIN alarm_box ON alarm_box.id = t_lighting.ALARMBOXID where LIGHTINGID is NULL and  deleted = '0' OR LIGHTINGID=''")
	List<LightingWithAlarm> getAlarmNotBind();
	
	@Select("SELECT  *  FROM 	t_lighting right JOIN t_bright ON t_bright.NODE_ID = t_lighting.LAMPSID where LIGHTINGID is NULL  OR LIGHTINGID=''")
	List<LightingWithLamps> getBrightNotBind();
	
	@Select("SELECT	*  FROM 	t_lighting RIGHT JOIN camera ON CAMERAID =id WHERE	LIGHTINGID IS NULL AND deleted = '0' OR LIGHTINGID = ''")
	List<LightingWithCamera> getCameraNotBind();


}
