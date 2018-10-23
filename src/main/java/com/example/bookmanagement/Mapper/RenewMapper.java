package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Renew;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RenewMapper {

    @Insert("insert into Renew (book_debit_id,renew_time,renew_day) values (#{renew.book_debit_id},#{renew.time},#{renew.day})")
    int addRenew(@Param("renew") Renew renew);

    @Select("select * from Renew")
    List<Renew> findRenews();

    @Select("select book_debit_id,renew_time,renew_day from BookDebit,Renew " +
            "where BookDebit.user_identification=#{user_identification} and " +
            "Renew.book_debit_id=BookDebit.ID")
    List<Renew> findUserRenews(String user_identification);

    @Select("select * from Renew where book_debit_id=#{book_id}")
    Renew findRenew(int book_id);
}
