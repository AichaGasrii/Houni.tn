package com.esprit.achat.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
//    @Before("execution( * com.example.SalmaKhemiri.generic.IGenericServiceImp.add*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {

        String name = joinPoint.getSignature().getName();

        log.info("In method :" + name );
    }


   @After("execution(* com.esprit.achat.services.*.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {

        String name = joinPoint.getSignature().getName();

        log.info("Out of method :" + name );
    }
}
