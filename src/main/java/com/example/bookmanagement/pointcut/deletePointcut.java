package com.example.bookmanagement.pointcut;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class deletePointcut {

    @Pointcut("execution(* com.example.bookmanagement.service.*.delete*(..))")
    public void deleteUser(){}

    @Pointcut("execution(* com.example.bookmanagement.service.ShelfObtainedService.delete*(..)) ||  " +
            "execution(* com.example.bookmanagement.service.ShelfObtainedService.select*(..))")
    public void selectShelfObtained(){}
}
