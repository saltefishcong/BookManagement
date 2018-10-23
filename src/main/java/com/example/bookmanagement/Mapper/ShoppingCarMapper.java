package com.example.bookmanagement.Mapper;

import com.example.bookmanagement.eity.ShoppingCar;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShoppingCarMapper {

    @Insert("insert into ShoppingCar (user_identification,book_identification) values" +
            " (#{car.user_identification},#{car.book_identification}) ")
    int addShoppingCar(ShoppingCar car);

    @Delete("delete from ShoppingCar where user_identification=#{car.user_identification} " +
            "and book_identification=#{car.book_identification}")
    int deleteShoppingCar(ShoppingCar car);

    @Select("select identification,name,author,introduction,flag from Book,ShoppingCar" +
            " where ShoppingCar.book_identification=#{Book.identification}" +
            " and ShoppingCar.user_identification=#{user_identification}")
    List<Map<String, Object>> findUserShoppingCar(String user_identification);
}
