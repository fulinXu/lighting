package com.example.demo;


import landsky.basic.common.utils.chief.redis.annotation.EnableRedis;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import landsky.basic.LandskyBasic;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//服务间调用方法的注解
@EnableFeignClients({LandskyBasic.PACKAGE,"com.lighting.business.*"})
@EnableEurekaClient
@ComponentScan(basePackages = {"com.lighting.*","landsky.basic"})
@EnableRedis
@EnableRabbit
@EnableElasticsearchRepositories(basePackages = {"com.lighting.business.*"})
public class LightingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightingApplication.class, args);
	}

}
