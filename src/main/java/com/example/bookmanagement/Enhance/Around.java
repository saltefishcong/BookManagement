package com.example.bookmanagement.Enhance;

import com.example.bookmanagement.eity.Administrator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class Around {

    @org.aspectj.lang.annotation.Around("com.example.bookmanagement.pointcut.addPointcut.addAdministrator()")
        public Administrator addAministrator(ProceedingJoinPoint pj) throws  Throwable{   //环绕增强方法返回值必须和增强方法返回值一样
        Object[] objects=pj.getArgs();
        Administrator amdin=null;
        if(objects[0] instanceof Administrator){
            amdin=(Administrator)objects[0];
        }
        Object obect=pj.proceed();
        System.out.println("新管理员标识为:   "+amdin.getIdentification()+"  管理员名称：  "+amdin.getName()
                +"  管理员密码: "+amdin.getPassword()+",  注册时间为:  "+new Date());
        return  (Administrator) obect;
    }

}
