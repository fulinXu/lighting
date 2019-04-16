//package com.example.demo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.FileOutConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.ITypeConvert;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.TemplateConfig;
//import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
//import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//public class MyBatisPlusGenerator {
//
//	public static void main(String[] args) {
//		// 代码生成器
//		AutoGenerator mpg = new AutoGenerator();
//
//		// 全局配置
//		GlobalConfig gc = new GlobalConfig();
//		String projectPath = System.getProperty("user.dir");
//		gc.setOutputDir(projectPath + "/src/main/java");
//		gc.setAuthor("Xu Fulin");
//		gc.setOpen(false);
//		// gc.setSwagger2(true);
//		// gc.setActiveRecord(true);
//		gc.setDateType(DateType.ONLY_DATE);
//		mpg.setGlobalConfig(gc);
//
//		// 数据源配置
//		mpg.setDataSource(new DataSourceConfig().setDbType(DbType.MYSQL).setUrl(
//				"jdbc:mysql://localhost:3306/sensor?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT")
//				// dsc.setSchemaName("public");
//				.setDriverName("com.mysql.cj.jdbc.Driver").setUsername("root").setPassword("123456")
//				.setTypeConvert(new MySqlTypeConvert() {
//					@Override
//					public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
//						// TODO Auto-generated method stub
//						/*
//						 * if(fieldType.toLowerCase().contains("datetime")) { return DbColumnType.DATE;
//						 * }
//						 */
//						return super.processTypeConvert(globalConfig, fieldType);
//					}
//				}));
//
//		// 包配置
//		mpg.setPackageInfo(new PackageConfig().setModuleName("device").setController("controller")
//				.setEntity("entity").setMapper("mapper").setService("service").setServiceImpl("service.impl")
//				.setParent("com.lighting.business"));
//
//		// 自定义配置
//		/*
//		 * InjectionConfig cfg = new InjectionConfig() {
//		 * 
//		 * @Override public void initMap() { // to do nothing } };
//		 */
//
//		// 如果模板引擎是 freemarker
//		String templatePath = "/templates/mapper.xml.ftl";
//		// 如果模板引擎是 velocity
//		// String templatePath = "/templates/mapper.xml.vm";
//
//		// 自定义输出配置
//		List<FileOutConfig> focList = new ArrayList<>();
//		// 自定义配置会被优先输出
//		/*
//		 * focList.add(new FileOutConfig(templatePath) {
//		 * 
//		 * @Override public String outputFile(TableInfo tableInfo) { // 自定义输出文件名 return
//		 * projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/" +
//		 * tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML; } });
//		 */
//
//		// cfg.setFileOutConfigList(focList);
//		// mpg.setCfg(cfg);
//
//		// 配置模板
//		TemplateConfig templateConfig = new TemplateConfig();
//
//		// 配置自定义输出模板
//		// templateConfig.setEntity();
//		// templateConfig.setService();
//		// templateConfig.setController();
//
//		templateConfig.setXml(null);
//		mpg.setTemplate(templateConfig);
//
//		// 策略配置
//		StrategyConfig strategy = new StrategyConfig();
//		strategy.setNaming(NamingStrategy.underline_to_camel);
//		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
////		strategy.setSuperEntityClass("landsky.adscreen.base.BaseEntity");
//		// strategy.setEntityLombokModel(true);
//		strategy.setRestControllerStyle(true);
////		strategy.setSuperControllerClass("landsky.adscreen.base.BaseController");
//		strategy.setInclude("t_lighting");
////		strategy.setSuperEntityColumns("id", "create_time", "update_time", "deleted");
//		strategy.setControllerMappingHyphenStyle(true);
//		strategy.setTablePrefix("t_");
//		mpg.setStrategy(strategy);
//		// mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//		mpg.execute();
//	}
//
//}
