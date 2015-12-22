package com.aop.aspectj;

import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2015/10/27 0027.
 */
@Aspect
@Component
public class PersonAspectj {

    @Before("execution(public String com.aop.controller.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        try {
            Object [] args = joinPoint.getArgs();
            BeanUtils.copyProperties(args[0],args[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(methodName + ":" );
    }

    @AfterThrowing(pointcut="execution(public String com.aop.controller.*.*(..))",throwing="e")
    public void afterMethod(JoinPoint joinPoint , Exception e){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("An exception "+e +"has been throwing in "+ methodName + "()");
    }
}
