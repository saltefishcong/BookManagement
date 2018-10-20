package com.example.bookmanagement.control;

import com.example.bookmanagement.eity.Book;
import com.example.bookmanagement.eity.ShelfObtained;
import com.example.bookmanagement.service.ShelfObtainedService;
import com.example.bookmanagement.service.checkService;
import com.example.bookmanagement.verification.add;
import com.example.bookmanagement.verification.status;
import com.example.bookmanagement.verification.update;
import com.example.bookmanagement.view.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ShelfObtainedController {

    @Autowired
    private ShelfObtainedService service;

    @RequestMapping(value = "/addShelfObtained", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    @ResponseBody
    public ResponseVO<ShelfObtained> addShelfObtained(@Validated(value = {add.class}) ShelfObtained shelfObtained
             ,@NotNull String author,@NotNull String introduction)
            throws Exception {
        shelfObtained.setOnline_book_num(shelfObtained.getBook_num());
        return new ResponseVO<>(200
                , "上架成功"
                , service.addShelfObtained(shelfObtained,author,introduction));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/deleteShelfObtained", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<List<Book>> deleteShelfObtained(@NotNull String book_name) throws Exception {
        return new ResponseVO<>(200
                , "下架成功"
                , service.deleteShelfObtained(book_name));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/addNum", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Book> addBookNum(@Validated(value = {update.class}) ShelfObtained shelfObtained)
            throws Exception {
        return new ResponseVO<>(200
                , "增加成功"
                , service.addBookNum(shelfObtained));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/deleteNum", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<List<Book>> deleteBookNum(@Validated(value = {update.class}) ShelfObtained shelfObtained)
            throws Exception {
        return new ResponseVO<>(200
                , "删除成功"
                ,  service.deleteBookNum(shelfObtained));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/updateShelfObtainedStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<ShelfObtained> updateStatus(@Validated(value = {status.class}) ShelfObtained shelfObtained)
            throws Exception {
        return new ResponseVO<>(200
                , "更改图书状态成功"
                , service.updateStatus(shelfObtained));
    }
}
