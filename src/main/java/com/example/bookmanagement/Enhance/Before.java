package com.example.bookmanagement.Enhance;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Before {

    @org.aspectj.lang.annotation.Before("com.example.bookmanagement.pointcut.Pointcut.crd()")
    public void addBefore(){
          System.out.println("事务监控开启: ");
    }

}
