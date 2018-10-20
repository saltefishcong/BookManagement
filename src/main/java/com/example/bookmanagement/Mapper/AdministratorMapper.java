package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.Administrator;
import com.example.bookmanagement.eity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdministratorMapper {

    @Insert("insert into Administrator (identification,name,password) values " +
            "(#{administrator.identification},#{administrator.name},#{administrator.password})")
    int addAdministrator(@Param("administrator") Administrator administrator);    /*  管理员注册  */

    @Select("select identification,name from Administrator where identification=#{administrator.identification} " +
            "and password=#{administrator.password}")
    Administrator findAministrator(@Param("administrator") Administrator administrator);   /*  查询管理员是否存在  */

    @ResultType(User.class)
    @Select("select username,identification from User")
    List<User> findList();  /*  返回所有注册的用户  */
}
