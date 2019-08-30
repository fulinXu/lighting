//package com.lighting.business.device.controller;
//
//import com.lighting.business.device.entity.Lighting;
//import com.lighting.business.device.service.EsOperate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/esOperate")
//public class EsCRUD {
//    @Autowired
//    private EsOperate esOperate;
//
//    @PostMapping("singleInsert")
//    public void SingleInsert(){
//        Lighting lighting = new Lighting("0123456789","0123456789","0123456789","0123456789","0123456789","0123456789","0123456789","0123456789","0123456789","0123456789","0123456789","0123456789","0123456789",30.157,0,"0123456789",30.145);
//        System.out.println("-------------"+esOperate.save(lighting));
//    }
//
//    @GetMapping("getAll")
//    public void QueryAll(){
//        Iterable<Lighting> lightings = esOperate.findAll();
//        for (Lighting lighting : lightings){
//            System.out.println(lighting);
//        }
//    }
//
//}
