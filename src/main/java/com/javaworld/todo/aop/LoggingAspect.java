package com.javaworld.todo.aop;

import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || " +
              "within(@org.springframework.stereotype.Service *) || " +
              "within(@org.springframework.stereotype.Repository *)")
    public void applicationBeans() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethods() {}

    @Around("applicationBeans() && publicMethods()")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long startTime = System.currentTimeMillis();

        log.info("Starting execution of {}.{} at {}", 
                 className, methodName, Instant.now());

        try {
            Object result = joinPoint.proceed();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            log.info("Execution of {}.{} completed in {} ms at {}",
                     className, methodName, duration, Instant.now());

            return result;

        } catch (Exception exception) {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            log.error("Execution of {}.{} failed after {} ms at {} with error: {}",
                      className, methodName, duration, Instant.now(), exception.getMessage());

            throw exception;
        }
    }
}
