package com.example.demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 统一处理异常
 */
@ControllerAdvice
public class ErrorAdive {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HashMap<String, Object> exceptionAdvie(Exception e) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "-1");
        result.put("msg", e.getMessage());
        return result;
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public HashMap<String, Object> arithmeticAdvie(ArithmeticException e) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "-2");
        result.put("msg", e.getMessage());
        return result;
    }

}
