package com.example.bookmanagement.pointcut;

import org.springframework.stereotype.Component;

@Component
public class Pointcut {

    @org.aspectj.lang.annotation.Pointcut("execution(* com.example.bookmanagement.service.*.add*(..))")
    public void crd(){

    }
}
