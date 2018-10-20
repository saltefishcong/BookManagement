package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Overdue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OverdueMapper {

    @Insert("insert into Overdue (book_debit_id,overdue_time,overdue_cost,flag) values " +
            "(#{overdue.book_debit_id},#{overdue.overdue_time},#{overdue.overdue_cost},0)")
    int addOverdue(@Param("overdue")Overdue overdue);

    @Update("update Overdue set flag =#{flag} where book_debit_id=#{book_debit_id} ")
    int UpdateFlag(boolean flag,int book_debit_id);
}
