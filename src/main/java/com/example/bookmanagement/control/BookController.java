package com.example.bookmanagement.control;

import com.example.bookmanagement.eity.Book;
import com.example.bookmanagement.service.BookService;
import com.example.bookmanagement.verification.add;
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
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping(value="/addBook",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<Book> addBook(@Validated(value = {add.class}) Book book,@NotNull int book_num
        ,@NotNull int index) throws Exception{
        return new ResponseVO<>(200
                ,"添加成功"
                ,service.addBook(book,book_num,index));
    }

    @RequestMapping(value = "/findBook",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<Book> findBook(@NotNull String identification) throws Exception{
        Book book=service.findBook(identification);
        return new ResponseVO<>(200
                ,"返回成功"
                ,book);
    }

    @RequestMapping(value = "/deleteBook",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<Book> deleteBook(@NotNull String identification) throws Exception{
        Book book=service.deleteBook(identification);
        return new ResponseVO<>(200
                ,"删除成功"
                ,book);
    }

    @RequestMapping(value = "/updateBookStatus",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<Book> updateStatus(@Validated(value = {update.class}) Book book) throws Exception{
         return    new ResponseVO<>(200
                 ,"更改成功"
                 ,service.updateStatus(book));
    }

    @RequestMapping(value = "/findBooker",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<Book> findBooker(@NotNull String book_name) throws Exception{
       return new ResponseVO<>(200
               , "返回成功"
               ,service.findBooker(book_name));
    }

    @RequestMapping(value = "/deleteBooks",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVO<List<Book>> deleteBooks(@NotNull  String book_name) throws Exception{
       return new ResponseVO<>(200
               ,"删除同一本图书的所有书本成功"
               ,service.deleteBooks(book_name));
    }
}
