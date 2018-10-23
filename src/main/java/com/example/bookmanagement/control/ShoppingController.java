package com.example.bookmanagement.control;

import com.example.bookmanagement.eity.ShoppingCar;
import com.example.bookmanagement.service.ShoppingCarService;
import com.example.bookmanagement.view.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ShoppingController {

    @Autowired
    private ShoppingCarService service;

    @RequestMapping(value = "/addShoppingCar", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    @ResponseBody
    public ResponseVO<List<ShoppingCar>> addShoppingCar(@RequestBody List<ShoppingCar> cars) throws Exception {
        return new ResponseVO<>(200
                , "加入购物车成功"
                , service.addShoppingCar(cars));
    }

    @RequestMapping(value = "/deleteShoppingCar", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<List<ShoppingCar>> deleteShoppingCar(@RequestBody List<ShoppingCar> cars) throws Exception {
        return new ResponseVO<>(200
                , "删除购物车成功"
                , service.deleteShoppingCar(cars));
    }

    @RequestMapping(value = "/findUserShoppingCar", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<List<Map<String, Object>>> findUserShoppingCar() {
        return new ResponseVO<>(200
                , "查询购物车成功"
                , service.findUserShopping("o5dsc3l0ix"));
    }
}
