package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.ReturnMapper;
import com.example.bookmanagement.eity.*;
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
public class ReturnService {

    @Resource
    public ReturnMapper returnMapper;

    @Autowired
    private DebitService debit;

    @Autowired
    private OverdueService overdue;

    @Autowired
    private RenewService renew;

    @Autowired
    private checkService check;

    @Autowired
    private BookService book;

    @Autowired
    private ShelfObtainedService shelfObtained;

    @Autowired
    private timeFactory ti;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public List<Return> addReturn(List<Return> returns) throws TransException {
        String message = "";
        Return retrun = null;
        Debit det = null;
        Renew ren = null;
        Book bo=null;
        for (int i = 0; i < returns.size(); i++) {
            retrun = returns.get(i);
            det = debit.findDebitID(retrun.getBook_debit_id());
            bo=book.findBook(det.getBook_identification());
            check.checkObject(bo,"找不到相应的图书");
            long time = new Date().getTime();
            if (det.getExpected_return_time() - time < 0) {
                ren = renew.findRenew(retrun.getBook_debit_id());
                if (ren == null || ti.getRenewTime(ren.getDay()) + det.getExpected_return_time() - time < 0) {
                    overdue.addOverdue(new Overdue(retrun.getBook_debit_id()
                            , det.getExpected_return_time()
                            , ti.getCost(), false));
                }
            }
            det.setFlag(true);
            debit.updateDebit(det);
            bo.setFlag(false);
            book.updateStatus(bo);
            shelfObtained.addBookNumer(bo.getName());
            retrun.setActual_return_time(time);
            check.checkException(returnMapper.addReturn(retrun), "还书异常");
        }
        return returns;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public Return findReturn(int book_id) {
        return returnMapper.findReturn(book_id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public List<Return> findReturns() {
        return returnMapper.findReturns();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public List<Return> findUserReturns(String user_identification) {
        return returnMapper.findUserReturns(user_identification);
    }
}
