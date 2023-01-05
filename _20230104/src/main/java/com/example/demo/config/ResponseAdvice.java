package com.example.demo.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

/**
 * 统一返回数据的处理
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    /**
     *  内容是否需要重写
     *  返回true表示重写
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 方法返回之前调用此方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 构造统一返回对象
        HashMap<String,Object> result=new HashMap<>();
        result.put("code",200);
        result.put("msg","");
        result.put("data",body);
        return result;
    }
}
