package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Renew;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RenewMapper {

    @Insert("insert into Renew (book_debit_id,time,day) values (#{renew.book_debit_id},#{renew.time},#{renew.day})")
    int addRenew(@Param("renew") Renew renew);
}
