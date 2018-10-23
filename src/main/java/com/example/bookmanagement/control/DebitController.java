package com.example.bookmanagement.control;

import com.example.bookmanagement.eity.Debit;
import com.example.bookmanagement.service.DebitService;
import com.example.bookmanagement.verification.update;
import com.example.bookmanagement.view.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class DebitController {

    @Autowired
    private DebitService service;

    @RequestMapping(value = "/addDebit", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    @ResponseBody
    public ResponseVO<List<Debit>> addDebit(@RequestBody List<Debit> debits) throws Exception {
        return new ResponseVO<>(200
                , "借书成功"
                , service.addDebit(debits,"o5dsc3l0ix"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/updateDebit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Debit> updateDebit(@Validated(value = {update.class}) Debit debit) throws Exception {
        return new ResponseVO<>(200
                , "更改借阅记录成功"
                , service.updateDebit(debit));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value ="/findDebits" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<List<Debit>> findDebits(){
         return new ResponseVO<>(200
                 ,"查询成功"
                 ,service.findDebits());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @ResponseBody
    @RequestMapping(value = "/findUserDebits" ,method = RequestMethod.POST)
    public ResponseVO<List<Debit>> findUserDebits(@NotNull String user_identification){
        return new ResponseVO<>(200
                ,"查询成功"
                ,service.findUserDebits(user_identification));
    }

}
