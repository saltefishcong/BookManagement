package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Debit;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DebitMapper {

    @Insert("insert into BookDebit (user_identification,book_identification,time,expected_return_time,flag) values " +
            "(#{debit.user_identification},#{debit.book_identification},#{debit.time},#{debit.expected_return_time},0)")
    int addDebit(@Param("debit") Debit debit);

    @Update("update BookDebit set flag=#{debit.flag} where user_identification=#{debit.user_identification} " +
            " and book_identification=#{debit.book_identification}")
    int updateDebit(@Param("debit") Debit debit);

    @Select("select * from BookDebit where book_identification=#{book_identification}")
    Debit findDebit(String book_identification);

    @Select("select * from  BookDebit")
    List<Debit> findDebits();

    @Select("select * from BookDebit where user_identification=#{user_identification}")
    List<Debit> findUserDebits(String user_identification);
}

