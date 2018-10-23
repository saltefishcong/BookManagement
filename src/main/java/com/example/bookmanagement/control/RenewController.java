package com.example.bookmanagement.control;

import com.example.bookmanagement.eity.Renew;
import com.example.bookmanagement.service.RenewService;
import com.example.bookmanagement.verification.add;
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

import java.util.Date;
import java.util.List;

@Controller
public class RenewController {

    @Autowired
    private RenewService service;

        @RequestMapping(value = "/addRenew" ,method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<Object> addRenew(@Validated(value = {add.class}) Renew renew) throws Exception{
        long time=service.addRenew(renew,"o5dsc3l0ix");
        return new ResponseVO<>(200
                ,"续借成功: 还书时间为: "+ new Date(time)
                ,null);
    }

    @ResponseBody
    @RequestMapping(value = "/findRenews",method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<List<Renew>> findRenews(){
         return new ResponseVO<>(200
                 ,"查询成功"
                 ,service.findRenews());
    }

    @ResponseBody
    @RequestMapping(value = "/findUserRenews" ,method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<List<Renew>> findUserRenews(){
        return new ResponseVO<>(200
                ,"查询用户借阅记录成功"
                ,service.findUserRenews("o5dsc3l0ix"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @ResponseBody
    @RequestMapping(value = "/findRenew" ,method = RequestMethod.POST)
    public  ResponseVO<Renew> findRenew(int book_debit_id) throws Exception{
         return new ResponseVO<>(200
                 ,"查询成功"
                 ,service.findRenew(book_debit_id));
    }
}
