package com.example.bookmanagement.control;

import com.example.bookmanagement.eity.Return;
import com.example.bookmanagement.service.ReturnService;
import com.example.bookmanagement.view.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReturnController {

    @Autowired
    private ReturnService service;

    @RequestMapping(value = "/addReturn", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    @ResponseBody
    public ResponseVO<List<Return>> addReturn(@RequestBody List<Return> returns) throws Exception {
        return new ResponseVO<>(200
                , "还书成功"
                , service.addReturn(returns));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/findReturn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Return> findReturn(int book_debit_id) {
        return new ResponseVO<>(200
                , "查询成功"
                , service.findReturn(book_debit_id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/findReturns", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<List<Return>> findReturns() {
        return new ResponseVO<>(200
                , "查询还书记录成功"
                , service.findReturns());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/findUserReturns", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<List<Return>> findUserReturns() {
        return new ResponseVO<>(200
                , "查询用户还书记录成功"
                , service.findUserReturns("o5dsc3l0ix"));
    }
}
