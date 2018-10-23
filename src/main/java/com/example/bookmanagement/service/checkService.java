package com.example.bookmanagement.service;

import com.example.bookmanagement.eity.TransException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class checkService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void checkObject(Object object, String message) throws TransException {
        if (object == null) {
            throw new TransException(message);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void checkException(int x, String message) throws TransException {
        if (x < 0) {
            throw new TransException(message);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void checkStatus(boolean flag, String message) throws TransException {
        if (flag == false) {
            throw new TransException(message);
        }
    }
}
