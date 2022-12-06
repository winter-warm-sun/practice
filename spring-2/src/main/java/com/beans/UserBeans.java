package com.beans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class UserBeans {
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(name="user1")
    public User getUser1() {
        User user=new User();
        user.setId(1);
        user.setName("张三");
        return user;
    }
}
