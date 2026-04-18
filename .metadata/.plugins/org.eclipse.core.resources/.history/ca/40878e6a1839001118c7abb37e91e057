package com.example.demo.aop;

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

    @Before("execution(* com.example.demo.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("➡️ Entering: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.demo.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("⬅️ Exiting: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.service.*.*(..))",
                    returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("✅ Method: " + joinPoint.getSignature().getName()
                + " returned: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.demo.service.*.*(..))",
                   throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("❌ Exception in: " + joinPoint.getSignature().getName()
                + " → " + error.getMessage());
    }
}