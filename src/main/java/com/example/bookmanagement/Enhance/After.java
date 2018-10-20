package com.example.bookmanagement.Enhance;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class After {

    @org.aspectj.lang.annotation.After("com.example.bookmanagement.pointcut.Pointcut.crd()")
    public void addAfter(){
        System.out.println("事务监控结束 :");
    }
}
