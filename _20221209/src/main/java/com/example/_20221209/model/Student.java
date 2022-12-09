package com.example._20221209.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix="student2")
@Component
public class Student {
    private int id;
    private String name;
    private int age;
}
