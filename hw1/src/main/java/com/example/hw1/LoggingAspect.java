package com.example.hw1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("@annotation(com.example.hw1.InOutLogged)")
    public void processedMethod(){}

    @Before("processedMethod()")
    public void logInputParameters(JoinPoint jp) {
        Object[] args = jp.getArgs();

        String name = jp.getSignature().getName();
        logger.info(name);

        for (Object arg : args) {
            logger.info(String.format("Input:%s", arg));
        }
    }

    @AfterReturning(pointcut = "processedMethod()", returning = "result")
    public void logOutputresult(JoinPoint jp, Object result) {
        logger.info(String.format("Output:%s", result.toString()));
    }
}
