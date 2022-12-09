package com.example._20221209.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties("dbtypes")
public class ReadList {
    private List<String> name;
}
