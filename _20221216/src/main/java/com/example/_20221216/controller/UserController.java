package com.example._20221216.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {
    @RequestMapping("/sayhi")
    public String sayHi() {
        return "hello.html";
    }

    @RequestMapping("/json")
    public HashMap<String,String> retJson() {
        HashMap<String,String> map=new HashMap<>();
        map.put("CSDN","如风暖阳");
        map.put("Java","Java Value");
        map.put("Spring","Spring Value");
        return map;
    }
    //请求转发
    @RequestMapping("/hello")
    public String hello() {
        return "forward:/hello.html";
    }

    //请求重定向
    @RequestMapping("/hello2")
    public String hello2() {
        return "redirect:/hello.html";
    }

    @RequestMapping("/calc")
    public String calc(Integer num1,Integer num2) {
        if(num1==null||num2==null) {
            return "<h1>参数错误！</h1><a href='javascript:history.go(-1);'>返回</a>";
        }
        return "<h1>结果："+(num1+num2)+"</h1><a href='javascript:history.go(-1);'>返回</a>";
    }

    @RequestMapping("/login")
    public HashMap<String,Object> login(String username,String password) {
        HashMap<String,Object> map=new HashMap<>();
        int state=200;
        int data=-1;// 等于1表示登录成功，否则登录失败
        String msg="未知错误";
        if(StringUtils.hasLength(username)&&StringUtils.hasLength(password)) {
            if(username.equals("admin")&&password.equals("1234")) {
                data=1;
                msg="";
            }else {
                msg="用户名或密码不正确！";
            }
        }else { //参数为空
            msg="非法参数";
        }
        map.put("state",state);
        map.put("data",data);
        map.put("msg",msg);
        return map;
    }
}
