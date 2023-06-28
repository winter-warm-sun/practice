package com.beans;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class GettingAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        String customerName=(String) args[0];
        System.out.println(customerName+"!与您谈的愉快，慢走！");
    }
}
