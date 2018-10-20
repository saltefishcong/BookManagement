package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.AdministratorMapper;
import com.example.bookmanagement.eity.Administrator;
import com.example.bookmanagement.eity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class AdministratorService {

    @Resource
    private AdministratorMapper administratorMapper;

    @Autowired
    private checkService check;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Administrator addAdministrator(Administrator administrator) throws SQLException {
        check.checkException(administratorMapper.addAdministrator(administrator), "添加管理员失败");
        return administrator;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = true)
    public Administrator loginAdministrator(Administrator administrator) throws SQLException {
        Administrator admin = administratorMapper.findAministrator(administrator);
        check.checkObject(admin, "登录失败");
        return admin;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = true)
    public List<User> findList() {
        return administratorMapper.findList();
    }
}
