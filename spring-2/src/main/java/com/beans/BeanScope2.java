package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanScope2 {
    @Autowired
    private User user1;

    public User getUser() {
        User user=user1;
        return user;
    }
}
