package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Return;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReturnMapper {

    @Insert("insert into Returns (book_debit_id,actual_return_time) values (#{returns.book_debit_id},#{returns.actual_return_time})")
    int addReturn(@Param("returns") Return returns);

    @Select("select * from Returns where book_debit_id=#{book_debit_id}")
    Return findReturn(int book_debit_id);

    @Select("select * from Returns")
    List<Return> findReturns();

    @Select("select book_debit_id,actual_return_time from BookDebit,Returns " +
            "where BookDebit.user_identification=#{user_identification} and " +
            "Returns.book_debit_id=BookDebit.ID")
    List<Return> findUserReturns(String user_identification);
}
