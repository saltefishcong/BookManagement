package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.DebitMapper;
import com.example.bookmanagement.eity.Book;
import com.example.bookmanagement.eity.Debit;
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
public class DebitService {

    @Resource
    private DebitMapper debitMapper;

    @Autowired
    private checkService check;

    @Autowired
    private BookService book;

    @Autowired
    private ShelfObtainedService shelfObtained;

    @Autowired
    private OverdueService overdue;

    @Autowired
    private timeFactory ti;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public List<Debit> addDebit(List<Debit> debits, String user_identification) throws TransException {
        overdue.findUserOverdues(user_identification); //  判断用户是否有逾期的图书
        Debit debit = null;
        for (int i = 0; i < debits.size(); i++) {
            debit = debits.get(i);
            Book book2 = book.findBook(debit.getBook_identification());
            check.checkObject(book2, "找不到对应的书本");
            check.checkStatus(book2.isFlag() == false, "该图书不在馆");
            shelfObtained.deleteBookNumer(book2.getName());
            book2.setFlag(true);
            book.updateStatus(book2);
            long time = new Date().getTime();
            debit.setTime(time);
            debit.setExpected_return_time(time + ti.getDebitTime());
            debit.setFlag(false);
            check.checkException(debitMapper.addDebit(debit), "借书异常");
        }
        debit = null;
        return debits;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Debit updateDebit(Debit debit) throws TransException {
        check.checkObject(findDebitID(debit.getId()), "找不到借书记录");
        check.checkException(debitMapper.updateDebit(debit), "更改记录异常");
        return debit;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public List<Debit> findDebits() {
        return debitMapper.findDebits();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public List<Debit> findUserDebits(String user_identification) {
        return debitMapper.findUserDebits(user_identification);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = true)
    public Debit findDebitID(int book_id) throws TransException {
        Debit debit = debitMapper.findDebitID(book_id);
        check.checkObject(debit, "指定的借阅ID不存在");
        return debit;
    }
}
