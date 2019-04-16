package com.lighting.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 * 
 * @author xfl
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket deviceApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("灯杆").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.lighting.business.device.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	/*
	 * @Bean public Docket aspectApi() { return new
	 * Docket(DocumentationType.SWAGGER_2).groupName("aspect") .apiInfo(apiInfo())
	 * .select() .apis(RequestHandlerSelectors.basePackage("smartcity.aspect"))
	 * .paths(PathSelectors.any()) .build(); }
	 */

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("lighting Apis").description("Apis for lighting")
				// .termsOfServiceUrl("http://blog.didispace.com/")
				.contact(new Contact("Xu FUlin", "", "")).version("1.0").build();
	}
}
