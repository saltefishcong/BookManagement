package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Book;
import org.apache.ibatis.annotations.*;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from Book where identification=#{identification}")
    Book findBook(String identification);  /*  寻找图书 */

    @Select("select * from Book where name=#{book_name} limit 0,1")
    Book findBook(@Param("book_name") String book_name,Object ojbect); /*  寻找图书 */

    @Select("select * from Book where name=#{book_name} and flag=0 limit 0,1")
    Book findBooker(String book_name);    /*  借书的时候由借记类调用，查询一本同名没被借的书 */

    @ResultType(Book.class)
    @Select("select * from Book where name=#{book_name} and flag=0")
    List<Book> findBooks(String book_name);    /* 查询所有没有被借的同一本书 */

    @ResultType(Book.class)
    @Select("select * from Book where name=#{book_name} and flag=0 limit 0,#{book_num}")
    List<Book> findBooks(@Param("book_name") String book_name,@Param("book_num") int book_num);  /* 查询指定数量没有被借的同一本书 */

    @Insert("insert into Book (identification,name,author,introduction,flag) values " +
            "(#{book.identification},#{book.name},#{book.author},#{book.introduction},0)")
    int addBook(@Param("book") Book book);  /*  添加图书 */

    @Delete("delete from Book where identification=#{identification}")
    int deleteBook(String identification); /* 删除图书 */

    @Delete("delete from Book where name=#{book_name} and flag=0 ")
    int deleteBooks(String book_name);    /*  删除同一本所有没有被借的书  */

    @Update("update Book set flag=#{book.flag} where identification=#{book.identification}")
    int updateStatus(@Param("book") Book book);   /*  更改书本的在馆状态 */

    /** @ResultType(Book.class)
    @Select("select * from Book where name = EXISTS (select * from ShelfObtained where category=#{category})")
    List<Book> findCategoryList(int category);     返回类别相同的图书

    @ResultType(Book.class)
    @Select("select * from Book where name like '#{name}%' ")
    List<Book> findNameBlurryList(String name);   /  返回模糊查询名字相同的书本  **/

}
