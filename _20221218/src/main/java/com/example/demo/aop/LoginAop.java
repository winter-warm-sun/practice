package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect  // 标识当前类为一个切面
public class LoginAop {
    // 定义切点（拦截的规则），使用AspectJ表达式语法
    @Pointcut("execution(* com.example.demo.controller.UserController.*(..))")
    public void pointcut() {
    }

    // 前置通知
    @Before("pointcut()")
    public void doBefore() {
        System.out.println("执行了前置通知");
    }

    // 后置通知
    @After("pointcut()")
    public void doAfter() {
        System.out.println("执行了后置通知");
    }

    // 环绕通知
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object result=null;
        // 前置业务代码
        System.out.println("环绕通知的前置执行方法");
        try {
            // 执行目标方法
            result=joinPoint.proceed();
        }catch (Throwable e) {
            e.printStackTrace();
        }
        // 后置业务执行代码
        System.out.println("环绕通知的后置执行代码");
        return result;
    }
}
