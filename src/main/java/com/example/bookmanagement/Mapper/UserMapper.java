package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into User (identification,username,password,sex) values " +
            "(#{user.identification},#{user.username},#{user.password},#{user.sex})")
    int addUser(@Param("user") User user);    /*   用户注册  */

    @Update("update User set username=#{user.username} ,password=#{user.password}, sex=#{user.sex} " +
            " where identification= #{user.identification}")
    int UpdateInfo(@Param("user") User user);    /*   用户更改信息  */

    @Select("select * from User where identification=#{user.identification}")
    User findUser(User user);

    @Delete("delete from User where identification=#{user.identification}")
    int deleteUser(User user);

    @Select("select * from User where identification=#{user.identification} and password=#{user.password}")
    User loginUser(User user);
}
