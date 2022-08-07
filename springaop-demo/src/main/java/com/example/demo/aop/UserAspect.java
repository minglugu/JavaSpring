package com.example.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // 表明此类为一个切面
@Component
public class UserAspect {

    /**
     * 定义pointcut切点的前置通知
     * 在执行目标方法之前执行的方法，就叫做前置通知
     */
    @Before("pointcut()")
    public void doBefore(){
        System.out.println("前置通知:被执行了");
    }

    // 定义切点(pointcut)，这里使用AspectJ表达式语法.制定拦截的规则
    // 第一个“*”，拦截任意方法的返回值 + 空格（） + 包名底下的这个类(UserController),底下的所有方法(.*),
    // 参数是(..)。 .. 不定参数(可能是1，2，3个参数),
    // 最后的拦截，可以到方法的参数级别
    // e.g.: @Pointcut("execution(* com.example.demo.controller.UserController.*(..))")
    // Spring AOP Tutorial - with Aspectj Examples：https://www.youtube.com/watch?v=Og9Fyew8ltQ
    // https://www.geeksforgeeks.org/spring-aop-aspectj-annotation/
    @Pointcut("execution(* com.example.demo.controller.UserController.*(..))")

    // 其中pointcut方法为空方法，它不需要有方法体，此方法名就是起到一个“标识”的作用，标识下面的通知方法，
    // 具体指的是哪个切点（因为切点可能有很多个）
    public void pointcut() {}



}
