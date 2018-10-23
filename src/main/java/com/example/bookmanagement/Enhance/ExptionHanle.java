package com.example.bookmanagement.Enhance;

import com.example.bookmanagement.eity.TransException;
import com.example.bookmanagement.view.ResponseVO;
import org.springframework.transaction.UnexpectedRollbackException;
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


    @ExceptionHandler({TransException.class})
    @ResponseBody
    public ResponseVO<Object> tranExcption(TransException e){
        return new ResponseVO<>(500
                ,e.getMessage()
                ,null);
    }
}
