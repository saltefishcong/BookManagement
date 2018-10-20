package com.example.bookmanagement.pointcut;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class addPointcut {

    @Pointcut("execution(* com.example.bookmanagement.service.AdministratorService.add*(..))")
    public void addAdministrator(){}

    @Pointcut("execution(* com.example.bookmanagement.service.UserService.add*(..))")
    public void addUser(){}

    @Pointcut("execution(* com.example.bookmanagement.service.BookService.add*(..))")
    public void addBook(){}

    @Pointcut("execution(* com.example.bookmanagement.service.DebitService.add*(..))")
    public void addDebit(){}

    @Pointcut("execution(* com.example.bookmanagement.service.OverdueService.add*(..))")
    public void addOverdue(){}

    @Pointcut("execution(* com.example.bookmanagement.service.RenewService.add*(..))")
    public void addRenew(){}

    @Pointcut("execution(* com.example.bookmanagement.service.ReturnService.add*(..))")
    public void addReturn(){}

    @Pointcut("execution(* com.example.bookmanagement.service.ShelfObtainedService.add*(..))")
    public void addShelfObtained(){}

}
