package com.example.bookmanagement.control;

import com.example.bookmanagement.eity.User;
import com.example.bookmanagement.factory.IdentificationFactory;
import com.example.bookmanagement.service.UserService;
import com.example.bookmanagement.verification.Loign;
import com.example.bookmanagement.verification.add;
import com.example.bookmanagement.verification.delete;
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
public class UserController {

    @Autowired
    public UserService service;

    @Autowired
    public IdentificationFactory factory;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<User> addUser(@Validated(value = {add.class}) User user) throws Exception {
        user.setIdentification(factory.produceUserIdentification());
        return new ResponseVO<>(200
                , "注册成功" + "这是你的账号:" + user.getIdentification() + "请记住!"
                , service.addUser(user));
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<User> UpdateInfo(@Validated(value = update.class) User user) throws Exception {
        return new ResponseVO<>(200
                , "更改成功"
                , service.UpdateInfo(user));
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseVO<User> loginUser(@Validated(value = {Loign.class}) User user) throws Exception {
        return new ResponseVO<>(200
                , "登录成功"
                , service.loginUser(user));
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<User> deleteUser(@Validated(value = {delete.class}) User user) throws Exception {
        return new ResponseVO<>(200
                , "删除用户成功"
                , service.deleteUser(user));
    }
}
