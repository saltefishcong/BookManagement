package com.example.bookmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class checkService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void checkObject(Object object, String message) throws SQLException {
        if (object == null) {
            throw new SQLException(message);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void checkException(int x, String message) throws SQLException {
        if (x < 0) {
            throw new SQLException(message);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void checkStatus(boolean flag, String message) throws SQLException {
        if (flag == false) {
            throw new SQLException(message);
        }
    }
}
