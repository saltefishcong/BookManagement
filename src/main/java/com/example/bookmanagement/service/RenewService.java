package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.RenewMapper;
import com.example.bookmanagement.eity.Renew;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RenewService {

    @Resource
    public RenewMapper renewMapper;

    public int addRenew(Renew renew){
        return renewMapper.addRenew(renew);
    }
}
