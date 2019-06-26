package com.lighting.business.device.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lighting.business.device.entity.*;

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
	@Select("select l.*,a.device_name  as adscreenName,a.card_number as cardNumber,c.device_alias  as  deviceAliasc,b.device_alias as alarmboxName,d.device_name as sensorName,l.LIGHTINGNAME,	n.ON_OFF  as  onOff,n.BRIGHTNESS,n.NODE_ID,n.ALIAS,n.`STATUS` from t_lighting l "
			+ "left join (select * from t_ad_screen_device where deleted = 0) a on l.ADSCREENID=a.id "
			+ "left join (select * from camera where deleted = 0 and state!=3) c on l.CAMERAID=c.id "
			+ "left join (select * from alarm_box where deleted = 0) b on l.ALARMBOXID=b.id "
			+ "left join (select * from t_envir_devices where deleted = 0) d on l.SENSORID=d.device_id " 
			+ "left join  (select * from t_bright where isdeleted = 0)  n on l.LAMPSID=n.node_id "
			+ "${ew.customSqlSegment}")
	List<LightingWithOthers> getLightingList(Page<LightingWithOthers> page, @Param("ew") Wrapper<Lighting> wrapper);
	
	@Select("select l.*,n.voltage,c.state As cameraState,c.camera_uuid As cameraUuid,b.camera_uuid As alarmboxUuid,b.state As alarmState,n.verify_code As verifyCode,n.current_level As currentLevel,n.powerfactor,a.device_name,d.atmospheric_pressure As atmosphericPressure,d.temperature_soil As temperatureSoil,d.humidity_soil As humiditySoil,d.pm25,d.pm10,d.co2,d.density_gas As densityGas,d.illuminate,d.noise,n.platform_id As platformId,d.create_time As weatherCreateTime,f.create_time As waterCreateTime,d.`status` AS  sensorStatus,c.device_alias  as  device_aliasc,c.state AS camera_state,b.device_alias AS alarmboxName,d.device_name  AS sensorName,d.temperature,d.humidity,d.device_id,l.LIGHTINGNAME,	n.ON_OFF,n.BRIGHTNESS,n.NODE_ID,n.ALIAS,n.`STATUS`,a.device_name AS adDeviceName,f.water from t_lighting l  "
			+ "left join (select * from t_ad_screen_device where deleted = 0) a on l.ADSCREENID=a.id "
			+ "left join (select * from camera where deleted = 0 and state!=3) c on l.CAMERAID=c.id "
			+ "left join (select * from alarm_box where deleted = 0) b on l.ALARMBOXID=b.id "
			+ "left join (select h.atmospheric_pressure,h.temperature_soil,h.temperature,h.humidity_soil,g.`status`,h.humidity,h.pm25,h.pm10,h.co2,h.density_gas,h.illuminate,h.create_time,h.noise,g.device_name,g.device_id from (select * from t_envir_devices where deleted = 0) g LEFT JOIN  (select * from t_evir_weather_only where deleted = 0) h on h.device_id = g.device_id) d ON l.SENSORID = d.device_id " 
			+ "left join (select * from t_bright where isdeleted = 0) n on l.LAMPSID=n.node_id "
			+ "LEFT JOIN (select k.water,k.create_time,j.device_id from (select * from t_envir_hydrops_only where deleted = 0) k LEFT JOIN (select * from t_envir_devices where deleted = 0) j on k.device_id = j.device_id) f ON l.SENSORID = f.device_id  "
			+ "${ew.customSqlSegment}")
	List<LightingWithOthers> getLightingListById(@Param("ew") Wrapper<Lighting> wrapper);
	
	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  (select * from t_lighting  WHERE ISDELETED = 0) l  RIGHT JOIN t_bright n ON n.NODE_ID = l.LAMPSID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithLamps> getLampsListByLighting(Page<LightingWithLamps> page, @Param("ew") Wrapper<Lighting> wrapper);
	
	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  (select * from t_lighting  WHERE ISDELETED = 0) l  RIGHT JOIN t_ad_screen_device  n ON n.id = l.ADSCREENID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithAds> getAdsListByLighting(Page<LightingWithAds> page, @Param("ew") Wrapper<Lighting> wrapper);
	
	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  (select * from t_lighting  WHERE ISDELETED = 0) l  RIGHT JOIN camera  n ON n.id = l.CAMERAID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithCamera> getCameraListByLighting(Page<LightingWithCamera> page, @Param("ew") Wrapper<Lighting> wrapper);
	
	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  (select * from t_lighting  WHERE ISDELETED = 0) l  RIGHT JOIN alarm_box  n ON n.id = l.ALARMBOXID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithAlarm> getAlarmListByLighting(Page<LightingWithAlarm> page, @Param("ew") Wrapper<Lighting> wrapper);
	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  (select * from t_lighting  WHERE ISDELETED = 0) l  RIGHT JOIN ("
			+ "SELECT  d.device_type,d.deleted,d.device_id,d.project_id,d.area_id,d.device_name As deviceName,d.`status`,w.temperature,w.density_gas As densityGas,w.pm25,w.pm10,w.co2,w.temperature_soil As temperatureSoil,w.noise,w.illuminate,w.humidity,w.humidity_soil As humiditySoil,w.atmospheric_pressure As atmosphericPressure,w.create_time As weatherrecordtime,h.create_time As waterrecordtime,h.water  "
			+ " FROM t_envir_devices d"
			+ " LEFT JOIN t_evir_weather_only w ON d.device_id = w.device_id "
			+ " LEFT JOIN t_envir_hydrops_only h ON d.device_id = h.device_id"
			+ ")  n ON n.device_id = l.SENSORID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithSensor> getSensorListByLighting(Page<LightingWithSensor> page, @Param("ew") Wrapper<Lighting> wrapper);

	@Select("SELECT  n.*, l.LIGHTINGNAME  FROM  (select * from t_lighting  WHERE ISDELETED = 0) l  RIGHT JOIN t_evse_device  n ON n.DZBH = l.EVSEID  "
			+ "${ew.customSqlSegment}")
	List<LightingWithEvse> getEvseListByLighting(Page<LightingWithEvse> page, @Param("ew") Wrapper<Lighting> wrapper);

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
	
	@Select("SELECT  *  FROM 	t_lighting right JOIN t_ad_screen_device ON t_ad_screen_device.id = t_lighting.ADSCREENID "
			+"${ew.customSqlSegment}"
			+" and LIGHTINGID is NULL  and deleted = '0' OR LIGHTINGID='' and isdeleted = '0'")
	List<LightingWithAds> getAdscreenNotBind(@Param("ew") QueryWrapper<Lighting> wrapper);
	
	@Select("SELECT  *  FROM 	t_lighting right JOIN t_envir_devices ON t_envir_devices.device_id = t_lighting.SENSORID "
			+"${ew.customSqlSegment}"
			+" and LIGHTINGID is NULL and  deleted = '0' OR LIGHTINGID='' and isdeleted = '0'")
	List<LightingWithSensor> getSensorNotBind(@Param("ew") QueryWrapper<Lighting> wrapper);
	
	@Select("SELECT  *  FROM 	t_lighting right JOIN alarm_box ON alarm_box.id = t_lighting.ALARMBOXID "
			+"${ew.customSqlSegment}"
			+" and LIGHTINGID is NULL and  deleted = '0' OR LIGHTINGID='' and isdeleted = '0'")
	List<LightingWithAlarm> getAlarmNotBind(@Param("ew") QueryWrapper<Lighting> wrapper);
	
	@Select("SELECT  *  FROM  t_lighting l  right JOIN  t_bright  n ON  n.NODE_ID = l.LAMPSID  "
			+"${ew.customSqlSegment}"
			+" and l.LIGHTINGID is NULL  and  n.isdeleted='0'  OR  l.LIGHTINGID='' and l.isdeleted = '0'")
	List<LightingWithLamps> getBrightNotBind(@Param("ew") QueryWrapper<Lighting> wrapper);
	
	@Select("SELECT	*  FROM 	t_lighting RIGHT JOIN camera ON CAMERAID =id "
			+"${ew.customSqlSegment}"
			+" and LIGHTINGID IS NULL AND deleted = '0' OR LIGHTINGID = '' and isdeleted = '0'")
	List<LightingWithCamera> getCameraNotBind(@Param("ew") QueryWrapper<Lighting> wrapper);

	@Select("select * from t_lighting  ${ew.customSqlSegment} limit #{current}, #{pageSize}")
	List<Lighting>  selectListPage(@Param("ew") QueryWrapper<Lighting> wrapper, @Param("current") int current, @Param("pageSize") int pageSize);
}
