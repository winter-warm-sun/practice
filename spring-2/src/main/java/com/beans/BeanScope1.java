package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanScope1 {
    @Autowired
    private User user1;

    public User getUser() {
        User user=user1;
        user.setName("李四");
        return user;
    }
}
