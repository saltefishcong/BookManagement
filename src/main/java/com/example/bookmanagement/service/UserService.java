package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.UserMapper;
import com.example.bookmanagement.eity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

@Service
public class UserService {

    @Resource
    public UserMapper userMapper;

    @Autowired
    private checkService check;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public User addUser(User user) throws SQLException {
        check.checkException(userMapper.addUser(user), "用户注册失败");
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public User UpdateInfo(User user) throws SQLException {
        check.checkObject(loginUser(user),"请登录");
        check.checkException(userMapper.UpdateInfo(user), "更改用户信息异常");
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public User loginUser(User user) throws SQLException {
        check.checkObject(userMapper.loginUser(user), "登录失败");
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public User deleteUser(User user) throws SQLException {
        user = userMapper.findUser(user);
        check.checkObject(user, "找不到对应的用户");
        check.checkException(userMapper.deleteUser(user), "删除用户失败");
        return user;
    }
}
