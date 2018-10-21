package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.DebitMapper;
import com.example.bookmanagement.eity.Book;
import com.example.bookmanagement.eity.Debit;
import com.example.bookmanagement.eity.ShelfObtained;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

@Service
public class DebitService {

    @Resource
    public DebitMapper debitMapper;

    @Autowired
    public checkService check;

    @Autowired
    private BookService book;

    @Autowired
    private ShelfObtainedService shelfObtained;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Debit addDebit(Debit debit) throws SQLException {
        Book book2 =book.findBook(debit.getBook_identification());
        check.checkObject(book2,"找不到对应的书本");
        check.checkStatus(shelfObtained.findOnlineBookNum(book2.getName())>1?true:false,"书本已经没有库存了");
        book2.setFlag(false);
        ShelfObtained she=new ShelfObtained();
        she.setBook_name(book2.getName());
        she.setBook_num(1);
      //  shelfObtained.deleteBookNum(she);
        book.updateStatus(book2);
        check.checkException(debitMapper.addDebit(debit), "借书异常");
        return debit;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Debit updateDebit(Debit debit) throws SQLException {
        check.checkObject(findDebit(debit), "找不到借书记录");
        check.checkException(debitMapper.updateDebit(debit), "更改记录异常");
        return debit;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public Debit findDebit(Debit debit) {
        return debitMapper.findDebit(debit);
    }

}
