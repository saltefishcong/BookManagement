package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.ShoppingCarMapper;
import com.example.bookmanagement.eity.ShoppingCar;
import com.example.bookmanagement.eity.TransException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCarService {

    @Resource
    private ShoppingCarMapper shoppingCarMapper;

    @Autowired
    private checkService check;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public List<ShoppingCar> addShoppingCar(List<ShoppingCar> cars) throws TransException {
        for (int i = 0; i < cars.size(); i++) {
            check.checkException(shoppingCarMapper.addShoppingCar(cars.get(i)), "加入购物车异常");
        }
        return cars;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public List<ShoppingCar> deleteShoppingCar(List<ShoppingCar> cars) throws TransException {
        for (int i = 0; i < cars.size(); i++) {
            check.checkException(shoppingCarMapper.deleteShoppingCar(cars.get(i)), "删除购物车异常");
        }
        return cars;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = true)
    public List<Map<String, Object>> findUserShopping(String user_identification) {
        return shoppingCarMapper.findUserShoppingCar(user_identification);
    }
}
