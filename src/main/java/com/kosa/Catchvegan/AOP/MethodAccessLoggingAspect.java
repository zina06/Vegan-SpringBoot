package com.kosa.Catchvegan.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MethodAccessLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(MethodAccessLoggingAspect.class);

    @Around("execution(* com.kosa.Catchvegan.Service..*.*(..))")
    public Object logMethodTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        logger.info("{} took {}ms", joinPoint.getSignature().toShortString(), elapsedTime);
        return result;
    }

}
