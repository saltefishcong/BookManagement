package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.ReturnMapper;
import com.example.bookmanagement.eity.Return;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReturnService {

    @Resource
    public ReturnMapper returnMapper;

    public int addReturn(Return returns){
        return  returnMapper.addReturn(returns);
    }
}
