package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

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

    /**
     * 针对pointcut切点的后置通知
     */
    @After("pointcut()")
    public void doAfter(){
        System.out.println("后置通知：被执行了！");
    }

    /**
     * 返回之后的通知
     * AfterReturning是在After(后置通知)之前，执行的
     */
    @AfterReturning("pointcut()")
    public void doAfterReturning(){
        System.out.println("执行了AfterReturning的方法");
    }

    /**
     * 如果没有报异常，那么AfterThrowing是不会被执行的
     */
    @AfterThrowing("pointcut()")
    public void doAfterThrowing() {
        System.out.println("执行了AfterThrowing方法");
    }

    /**
     * Around，环绕方法，返回的是Object，是固定写法
     * 针对pointcut切点的环绕通知
     */

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        // spring 中的时间统计对象
        StopWatch stopWatch = new StopWatch();
        Object result = null;
        //System.out.println("环绕通知：前置方法。");
        try {
            stopWatch.start(); // 统计方法的执行时间，开始计时
            // 执行目标方法，以及目标方法所对应的相应的通知
            result = joinPoint.proceed();
            stopWatch.stop(); // 统计方法的执行时间，停止计时
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        // System.out.println("环绕通知，后置方法。");
        System.out.println(joinPoint.getSignature().getDeclaringTypeName() +"." +
                joinPoint.getSignature().getName() + "执行花费的时间："+
                stopWatch.getTotalTimeMillis() + "ms");
        return result;
    }
}
