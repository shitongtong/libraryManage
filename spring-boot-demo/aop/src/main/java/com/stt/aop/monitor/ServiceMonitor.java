package com.stt.aop.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-02-11.
 *
 * @author shitongtong
 */
@Aspect
@Component
public class ServiceMonitor {

    @AfterReturning("execution(* com.stt..*Service.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        System.out.println("Completed" + joinPoint);
    }
}
