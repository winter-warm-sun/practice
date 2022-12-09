package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class UserController {
    @ResponseBody//返回一个非静态页面的数据
    @RequestMapping("/sayhi")//设置路由地址
    public String sayHi() {
        return "Hello,Spring Boot!";
    }
}
