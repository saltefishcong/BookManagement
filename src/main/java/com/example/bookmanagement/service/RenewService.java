package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.RenewMapper;
import com.example.bookmanagement.eity.Debit;
import com.example.bookmanagement.eity.Renew;
import com.example.bookmanagement.eity.TransException;
import com.example.bookmanagement.factory.timeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class RenewService {

    @Resource
    public RenewMapper renewMapper;

    @Autowired
    private OverdueService overdue;

    @Autowired
    private DebitService debit;

    @Autowired
    private checkService check;

    @Autowired
    private timeFactory ti;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public long addRenew(Renew renew, String user_identification) throws TransException {
        overdue.findUserOverdues(user_identification);  // 查询用户是否逾期
        Debit debit1 = debit.findDebitID(renew.getBook_debit_id());
        long time = new Date().getTime();
        if (time - debit1.getExpected_return_time() > 0) {
            throw new TransException("该图书已经逾期,不能续借!");
        }
        renew.setTime(time);
        renew.setDay(ti.getRenewDay());
        check.checkException(renewMapper.addRenew(renew), "续借异常");
        return debit1.getExpected_return_time() + ti.getRenewTime(ti.getRenewDay());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = true)
    public Renew findRenew(int book_id) {
       return renewMapper.findRenew(book_id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public List<Renew> findRenews() {
        return renewMapper.findRenews();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public List<Renew> findUserRenews(String user_identification) {
        return renewMapper.findUserRenews(user_identification);
    }
}
