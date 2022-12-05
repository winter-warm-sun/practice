package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController4 {
    private UserService userService;

    //Setter注入
    @Autowired
    public void setUserService(UserService userService) {
        this.userService=userService;
    }

    public void sayHi() {
        userService.sayHi();
    }
}
