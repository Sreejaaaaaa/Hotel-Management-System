package com.example.billing.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log =
            LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.billing.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.billing.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Exiting: " + joinPoint.getSignature().getName());
    }
}