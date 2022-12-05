package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController2 {
    //属性注入
    @Autowired
    private UserService userService;

    public void sayHi() {
        userService.sayHi();
    }
}
