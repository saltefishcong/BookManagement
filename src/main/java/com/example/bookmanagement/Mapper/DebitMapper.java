package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Debit;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface DebitMapper {

    @Insert("insert into BookDebit (user_identification,book_identification,time,expected_return_time,flag) values " +
            "(#{debit.user_identification},#{debit.book_identification},#{debit.time},#{debit.expected_return_time},0)")
    int addDebit(@Param("debit") Debit debit);

    @Update("update BookDebit set flag=#{debit.flag} where ID=#{debit.id}")
    int updateDebit(@Param("debit") Debit debit);

    @Select("select * from BookDebit where book_identification=#{book_identification}")
    Debit findDebit(String book_identification);

    @Select("select * from  BookDebit")
    List<Debit> findDebits();

    @Select("select * from BookDebit where user_identification=#{user_identification} and flag=0")
    List<Debit> findUserDebits(String user_identification);

    @Select("select * from BookDebit where ID=#{book_id}")
    Debit findDebitID(int book_id);
}

