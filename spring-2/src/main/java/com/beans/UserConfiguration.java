package com.beans;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    public void sayHi() {
        System.out.println("你好，Configuration");
    }
}
