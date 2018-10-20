package com.example.bookmanagement.Enhance;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.bookmanagement.eity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Aspect
public class AfterReturn {

    @AfterReturning(pointcut ="execution(* com.example.bookmanagement.service.*.login*(..))",returning="flag")
    public void loginAferReturn(JoinPoint jp,boolean flag){
        if(flag ==true){
            String mess="";
            Object[] objects=jp.getArgs();
            for(Object object:objects){
                mess+=object;
            }
            System.out.println(mess+  "  成功登录! ");
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.bookmanagement.service.AdministratorService.findList(..))",returning = "list")
    public void findUserList(JoinPoint jp,List<User> list){
        if(list !=null){
            String mess="";
            Object[] objects=jp.getArgs();
            for(Object object:objects){
                mess+=object;
            }
            System.out.println("管理员 "+mess+" 查看了用户信息: 时间在  "+new Date());
        }
    }
}
