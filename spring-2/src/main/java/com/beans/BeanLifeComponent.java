package com.beans;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanLifeComponent implements BeanNameAware {
    @PostConstruct
    public void postConstruct() {
        System.out.println("执行了@PostConstruct");
    }

    public void init() {
        System.out.println("执行了init-method");
    }

    public void use() {
        System.out.println("使用bean");
    }

    @PreDestroy
    public void preDestory() {
        System.out.println("执行了@PreDestory");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("执行了Aware通知");
    }
}
