package com.example.bookmanagement.Enhance;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Aspect
public class Threows {

    @AfterThrowing(value = "com.example.bookmanagement.pointcut.Pointcut.crd()",throwing = "sql")
    public void addThrows(JoinPoint jp,Exception sql){
        Object[] objects=jp.getArgs();
        String mess="sql异常  "+sql.getMessage() +"  发生的线程: "+Thread.currentThread()+"  "+" 发生时间: " +new Date()+"  ";
        mess+="  参数列表: [ ";
        for(Object object: objects){
             mess+="  "+object +".  ";
        }
        mess+="]  异常所在类为:  "+jp.getSignature().getDeclaringTypeName()+ "  异常方法:  "+jp.getSignature().getName() +"  ";
        System.out.println(mess);
    }
}
