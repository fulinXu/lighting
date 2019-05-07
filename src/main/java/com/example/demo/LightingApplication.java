package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import landsky.basic.LandskyBasic;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//服务间调用方法的注解
@EnableFeignClients({LandskyBasic.PACKAGE,"com.lighting.business.*"})
@EnableEurekaClient
@ComponentScan(basePackages = {"com.lighting.*","landsky.basic"}) 
public class LightingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightingApplication.class, args);
	}

}
