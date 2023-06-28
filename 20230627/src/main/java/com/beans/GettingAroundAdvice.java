package com.beans;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GettingAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object args[]=methodInvocation.getArguments();
        String customerName=(String) args[0];
        // 在目标方法执行前调用
        System.out.println(customerName+"执行前");
        Object object=methodInvocation.proceed();  // 通过反射调用执行方法
        // 在目标方法执行后调用
        System.out.println(customerName+"执行后");
        return object;
    }
}
