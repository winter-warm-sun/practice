package com.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class UserBeans {
    @Bean(name="u1")
    public User getUser1() {
        User user=new User();
        user.setId(1);
        user.setName("张三");
        return user;
    }
    @Bean(name="u2")
    public User getUser2() {
        User user=new User();
        user.setId(2);
        user.setName("李四");
        return user;
    }
}
