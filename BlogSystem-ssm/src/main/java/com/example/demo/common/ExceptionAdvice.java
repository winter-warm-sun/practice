package com.example.demo.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常类的统一处理
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Object exceptionAdvice(Exception e) {
        return Result.fail(-1,e.getMessage());
    }
}
