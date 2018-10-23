package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.OverdueMapper;
import com.example.bookmanagement.eity.Book;
import com.example.bookmanagement.eity.Overdue;
import com.example.bookmanagement.eity.TransException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OverdueService {

    @Resource
    public OverdueMapper overdueMapper;

    @Autowired
    private checkService check;

    @Autowired
    private BookService book;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Overdue addOverdue(Overdue overdue) throws TransException {
        check.checkException(overdueMapper.addOverdue(overdue), "添加逾期记录异常");
        return overdue;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Overdue updateOverdue(Overdue overdue) throws TransException {
        check.checkException(overdueMapper.UpdateFlag(overdue), "更改逾期记录异常");
        return overdue;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = true)
    public boolean findUserOverdues(String user_identification) throws TransException {
        List<Map<String, Object>> list = overdueMapper.findUserOverdues(user_identification);
        if (list.size()>0) {
            String message = "";
            for (Map<String, Object> map : list) {
                Book book2 = book.findBook((String) map.get("book_identification"));
                long time = (Long) map.get("overdue_time");
                message += "你的图书 :" + book2.getName() + "已经逾期, 时间为:  " + new Date(time) +
                        "  逾期费用为: " + map.get("overdue_cost") + " 元!  \n";
            }
            throw new TransException(message);
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public Overdue findOverdue(int book_id) {
        return overdueMapper.findOverdue(book_id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = true)
    public List<Overdue> findOverdues() {
        return overdueMapper.findOverdues();
    }
}
