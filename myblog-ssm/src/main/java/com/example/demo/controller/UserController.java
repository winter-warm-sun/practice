package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public HashMap<String, Object> login(String username, String password) {
        HashMap<String, Object> result = new HashMap<>();
        int data = -1;// 等于1表示登录成功
        String msg = "未知错误";
        // 1.非空效验
        if (StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            //2.调用userservice进行查询
            User user = userService.login(username, password);
            if (user != null && user.getUserid() > 0) {
                //登录成功
                data = 1;
                msg = "";
            } else {
                msg = "非法参数";
            }
            result.put("succ", 200);
            result.put("data", data);
            result.put("msg", msg);
        }
        return result;
    }
}
