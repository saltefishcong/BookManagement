package com.example.bookmanagement.control;

import com.example.bookmanagement.eity.Debit;
import com.example.bookmanagement.service.DebitService;
import com.example.bookmanagement.verification.add;
import com.example.bookmanagement.verification.update;
import com.example.bookmanagement.view.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DebitController {

    @Autowired
    private DebitService service;

    @RequestMapping(value = "/addDebit", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    @ResponseBody
    public ResponseVO<Debit> addDebit(@Validated(value = {add.class}) Debit debit) throws Exception {
        return new ResponseVO<>(200
                , "借书成功"
                , service.addDebit(debit));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/updateDebit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Debit> updateDebit(@Validated(value = {update.class}) Debit debit) throws Exception {
        return new ResponseVO<>(200
                , "更改借阅记录成功"
                , service.updateDebit(debit));
    }
}
