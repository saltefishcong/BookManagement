package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Overdue;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OverdueMapper {

    @Insert("insert into Overdue (book_debit_id,overdue_time,overdue_cost,flag) values " +
            "(#{overdue.book_debit_id},#{overdue.overdue_time},#{overdue.overdue_cost},0)")
    int addOverdue(@Param("overdue") Overdue overdue);

    @Update("update Overdue set flag =#{overdue.flag} where book_debit_id=#{overdue.book_debit_id} ")
    int UpdateFlag(Overdue overdue);

    @Select("select book_identification,overdue_time,overdue_cost from BookDebit,Overdue " +
            "where BookDebit.ID=Overdue.book_debit_id and Overdue.flag=0 " +
            "and BookDebit.user_identification=#{user_identification}")
    List<Map<String, Object>>  findUserOverdues(String user_identification);

    @Select("select * from Overdue where book_debit_id=#{book_id}")
    Overdue findOverdue(int book_id);

    @Select("select * from Overdue")
    List<Overdue> findOverdues();
}
