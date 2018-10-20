package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.OverdueMapper;
import com.example.bookmanagement.eity.Overdue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OverdueService {

    @Resource
    public OverdueMapper overdueMapper;

    public int addOverdue(Overdue overdue){
        return overdueMapper.addOverdue(overdue);
    }

    public int updateOverdue(boolean flag,int book_debit_id){
        return overdueMapper.UpdateFlag(flag,book_debit_id);
    }
}
