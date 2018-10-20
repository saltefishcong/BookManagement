package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Debit;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DebitMapper {

    @Insert("insert into BookDebit (user_identification,book_identification,time,expected_return_time,flag) values " +
            "(#{debit.user_identification},#{debit.book_identification},#{debit.time},#{debit.expected_return_time},0)")
    int addDebit(@Param("debit") Debit debit);

    @Update("update BookDebit set flag=#{debit.flag} where user_identification=#{debit.user_identification} " +
            " and book_identification=#{debit.book_identification}")
    int updateDebit(@Param("debit") Debit debit);

    @Select("select * from BookDebit where user_identification=#{debit.user_identification} " +
            " and book_identification=#{debit.book_identification}")
    Debit findDebit(@Param("debit") Debit debit);
}
