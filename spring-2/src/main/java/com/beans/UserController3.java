package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController3 {
    private UserService userService;

    //构造方法注入
    @Autowired
    public UserController3(UserService userService) {
        this.userService = userService;
    }

    public void sayHi() {
        userService.sayHi();
    }
}
