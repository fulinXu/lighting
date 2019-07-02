![landsky](http://192.168.30.250/landsky/landsky-basic/raw/master/landsky.jpg)

#### 部分代码说明


## 文件结构说明

```
├───main
│   ├───java
│   │   └───com
│   │       ├───example
│   │       │   └───demo
│   │       │           LightingApplication.java
│   │       │           MyBatisPlusGenerator.java
│   │       │           ServletInitializer.java
│   │       │
│   │       └───lighting
│   │           └───business
│   │               ├───config
│   │               │       DruidConfig.java 
│   │               │       MyBatisPlusConfig.java
│   │               │       Swagger2Config.java
│   │               │
│   │               ├───device
│   │               │   ├───controller
│   │               │   │       LightingController.java   灯杆操作
│   │               │   │
│   │               │   ├───entity
│   │               │   │   │   Lighting.java
│   │               │   │   │   LightingWithAds.java  //灯杆与广告屏联表查询返回的实体类
│   │               │   │   │   LightingWithAlarm.java  //灯杆与一键报警联表查询返回的实体类
│   │               │   │   │   LightingWithCamera.java  //灯杆与摄像头联表查询返回的实体类
│   │               │   │   │   LightingWithEvse.java  //灯杆与充电桩联表查询返回的实体类
│   │               │   │   │   LightingWithLamps.java  //灯杆与灯具联表查询返回的实体类
│   │               │   │   │   LightingWithOthers.java  //灯杆与所有设备关键字段联表查询返回的实体类
│   │               │   │   │   LightingWithSensor.java  //灯杆与工控机联表查询返回的实体类
│   │               │   │   │
│   │               │   │   └───model
│   │               │   ├───mapper
│   │               │   │       LightingMapper.java
│   │               │   │
│   │               │   └───service
│   │               │       │   ILightingService.java
│   │               │       │
│   │               │       └───impl
│   │               │               LightingServiceImpl.java
│   │               │
│   │               └───utils
│   │                       EsClientUtil.java
│   │
│   └───resources
│       │   application-dev.properties
│       │   application-prod.properties
│       │   application-yun.properties
│       │   application.properties
│       │
│       └───templates
└───test
    │   test10.iml
    │
    ├───java
    │   └───com
    │       └───example
    │           └───demo
    │                   LightingApplicationTests.java
    │
    └───resources
```