package com.example._20221213.controller;

import com.example._20221213.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody // 作用：表示返回的是一个非静态页面的数据
@RequestMapping("/user") // 类上面的RequestMapping可以省略
public class UserController {
    @RequestMapping(value = "/sayhi",method = RequestMethod.GET )
    public String sayHi() {
        return "你好，世界";
    }
    @RequestMapping("/getid")
    public Integer getID(Integer id) {
        return id;
    }
    @RequestMapping("/login")
    public String login(@RequestParam(value = "name",required = false) String username, String password) {
        return "用户名："+username+" | 密码："+password;
    }
    @RequestMapping("/reg")
    public String user(@RequestBody User user) {
        return "用户信息："+user;
    }
}
