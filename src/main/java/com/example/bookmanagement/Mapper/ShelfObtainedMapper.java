package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.ShelfObtained;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShelfObtainedMapper {

    @Insert("insert into ShelfObtained (book_name,category,category_num,book_num,online_book_num,flag) values " +
            "(#{shelfObtained.book_name},#{shelfObtained.category}," +
            "#{shelfObtained.category_num},#{shelfObtained.book_num},#{shelfObtained.online_book_num},0)")
    int addShelfObtained(@Param("shelfObtained") ShelfObtained shelfObtained);

    @Update("update ShelfObtained set book_num=book_num - #{shelfObtained.book_num} ," +
            "online_book_num=online_book_num - #{shelfObtained.book_num}" +
            " where book_name =#{shelfObtained.book_name}")
    int deleteBookNum(@Param("shelfObtained") ShelfObtained shelfObtained);

    @Update("update ShelfObtained set online_book_num=online_book_num - 1 where book_name=#{book_name}")
    int deleteBookNumer(String book_name);

    @Update("update ShelfObtained set book_num=book_num + #{shelfObtained.book_num}" +
            ",online_book_num=online_book_num + #{shelfObtained.book_num} " +
            " where book_name =#{shelfObtained.book_name} ")
    int addBookNum(@Param("shelfObtained") ShelfObtained shelfObtained);

    @Select("select online_book_num  from ShelfObtained where book_name=#{book_name}")
    int selectOnlineBookNum(String book_name);

    @Update("update ShelfObtained set book_num=book_num-#{online_book_num}," +
            "online_book_num=0,flag=1 where book_name=#{book_name}")
    int deleteShelfObtained(@Param("book_name") String book_name, @Param("online_book_num") int online_book_num);

    @Select("select * from ShelfObtained where book_name=#{book_name}")
    ShelfObtained selectShelfObtained(String book_name);

    @Select("select flag from ShelfObtained where book_name=#{book_name}")
    int selectStatus(String book_name);

    @Update("update ShelfObtained set flag = #{shelfObtained.flag} where book_name= #{shelfObtained.book_name}")
    int updateStatus(@Param("shelfObtained") ShelfObtained shelfObtained);

    @Select("select * from ShelfObtained")
    List<ShelfObtained> selectShelfObtaineds();
}
