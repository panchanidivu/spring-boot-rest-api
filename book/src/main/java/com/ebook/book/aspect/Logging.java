package com.ebook.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class Logging {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.ebook.book.controller.*.*(..))")
    private void pointCutDeclaration(){

    }
    @Before("pointCutDeclaration()")
    private void before(JoinPoint joinPoint){
        logger.info("Before method: {}",joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "pointCutDeclaration()", returning = "result")
    private void afterReturning(JoinPoint joinPoint, Object result){
        logger.info("After returning method: {}",joinPoint.getSignature().getName());
        logger.info("Result: {}",result);
    }

    @After(value = "pointCutDeclaration()")
    private void after(JoinPoint joinPoint){
        logger.info("After method: {}",joinPoint.getSignature().getName());
        logger.info("after execution of {}:{}", joinPoint.getSignature().getDeclaringType(), joinPoint);
        
    }

    @AfterThrowing(value = "pointCutDeclaration()", throwing = "exception")
    private void afterThrowing(JoinPoint joinPoint, Exception exception){
        logger.info("After throwing method: {}",joinPoint.getSignature().getName());
        logger.info("Exception: {}",exception);
    }

    // @Around("pointCutDeclaration()")
    // private void around(JoinPoint joinPoint){
    //     logger.info("Around method: {}",joinPoint.getSignature().getName());
    //     logger.info("around execution of {}:{}", joinPoint.getSignature().getDeclaringType(), joinPoint);
    // }
    
    @Around("pointCutDeclaration()")
    private Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Around method: {}",proceedingJoinPoint.getSignature().getName());
        logger.info("around execution of {}:{}", proceedingJoinPoint.getSignature().getDeclaringType(), proceedingJoinPoint);

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        logger.info("Result: {}",result);
        return result;
    }
}
