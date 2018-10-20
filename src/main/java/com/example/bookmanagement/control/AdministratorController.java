package com.example.bookmanagement.control;

import com.example.bookmanagement.eity.Administrator;
import com.example.bookmanagement.eity.User;
import com.example.bookmanagement.factory.IdentificationFactory;
import com.example.bookmanagement.service.AdministratorService;
import com.example.bookmanagement.verification.Loign;
import com.example.bookmanagement.verification.add;
import com.example.bookmanagement.view.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdministratorController {

    @Autowired
    public AdministratorService service;

    @Autowired
    public IdentificationFactory factory;

    @RequestMapping(value = "/addAdministrator", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<Administrator> addAdministrator(@Validated(value = {add.class}) Administrator administrator)
            throws Exception {
        administrator.setIdentification(factory.produceAdministratorIdentification());
        return new ResponseVO<>(200
                , "管理员注册成功" + "请记住你的管理员账号:" + administrator.getIdentification()
                , service.addAdministrator(administrator));
    }

    @RequestMapping(value = "/loginAdministrator", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseVO<Administrator> loginAdministrator(@Validated(value = {Loign.class}) Administrator administrator)
            throws Exception {
        return new ResponseVO<>(200
                , "登录成功"
                , service.loginAdministrator(administrator));
    }

    @RequestMapping(value = "/findUserList", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<List<User>> findList() {
        return new ResponseVO<>(200
                , "查询成功"
                , service.findList());
    }
}
