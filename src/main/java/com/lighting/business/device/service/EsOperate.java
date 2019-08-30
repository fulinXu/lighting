package com.lighting.business.device.service;


import com.lighting.business.device.entity.Lighting;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description:定义ItemRepository 接口
 * @Param:
 * 	Lighting:为实体类
 * 	String:为Lighting实体类中主键的数据类型
 */
public interface EsOperate extends ElasticsearchRepository<Lighting,String> {

}
