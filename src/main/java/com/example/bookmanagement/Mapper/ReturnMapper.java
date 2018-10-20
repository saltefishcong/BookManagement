package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Return;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReturnMapper {

    @Insert("insert into Return (book_debit_id,actual_return_time) values (#{returns.book_debit_id},#{returns.actual_return_time})")
    int addReturn(@Param("returns") Return returns);
}
