package com.example.bookmanagement.Enhance;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@ControllerAdvice
public class ExptionHanle {

    @ExceptionHandler({BindException.class})
    @ResponseBody
    public String nullexcption(BindingResult rs){
        List<ObjectError> list=rs.getAllErrors();
        String erro="";
        for(ObjectError object:  list){
            erro=object.getDefaultMessage()+"   ";
        }
        return  erro;
    }


    @ExceptionHandler({Exception.class})
    @ResponseBody
    public String excption(Exception e){
        System.out.println(" -1 " +e.getMessage());
        return e.getMessage();
    }
}
