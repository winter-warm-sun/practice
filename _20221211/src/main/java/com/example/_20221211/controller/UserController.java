//package com.example._20221211.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@ResponseBody
//public class UserController {
//    //1.先得到日志对象(来自slf4j)
//    private static final Logger log=
//            LoggerFactory.getLogger(UserController.class);
//    @RequestMapping("/sayhi")
//    public void sayHi() {
//        //2.使用日志对象提供的打印方法进行日志打印
//        log.trace("我是trace");
//        log.debug("我是debug");
//        log.info("我是info");
//        log.warn("我是warn");
//        log.error("我是error");
//    }
//}
