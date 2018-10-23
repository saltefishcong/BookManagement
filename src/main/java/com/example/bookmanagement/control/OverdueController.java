package com.example.bookmanagement.control;

import com.example.bookmanagement.eity.Overdue;
import com.example.bookmanagement.service.OverdueService;
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

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class OverdueController {

    @Autowired
    private OverdueService service;

    @RequestMapping(value = "/addOverdue", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<Overdue> addOverdue(@Validated(value = {add.class}) Overdue overdue) throws Exception {
        return new ResponseVO<>(200
                , "添加逾期记录成功"
                , service.addOverdue(overdue));
    }

    @RequestMapping(value = "/updateOverdue", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<Overdue> updateOverdue(@Validated(value = {update.class}) Overdue overdue) throws Exception {
        return new ResponseVO<>(200
                , "更改逾期记录成功"
                , service.updateOverdue(overdue));
    }

    @ResponseBody
    @RequestMapping(value = "/findUserOverdues", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<Boolean> findUserOverdues(@NotNull String user_identification) throws Exception {
        return new ResponseVO<>(200
                , "查询成功"
                , service.findUserOverdues(user_identification));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/findOverdue", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Overdue> findOverdue(int book_id) {
        return new ResponseVO<>(200
                , "查询成功"
                , service.findOverdue(book_id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/findOverdues", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<List<Overdue>> findOverdues() {
        return new ResponseVO<>(200
                , "查询逾期记录成功"
                , service.findOverdues());
    }
}
