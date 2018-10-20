package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.ShelfObtainedMapper;
import com.example.bookmanagement.eity.Book;
import com.example.bookmanagement.eity.ShelfObtained;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class ShelfObtainedService {

    @Resource
    public ShelfObtainedMapper shelfObtainedMapper;

    @Autowired
    private checkService check;

    @Autowired
    private BookService book;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public ShelfObtained addShelfObtained(ShelfObtained shelfObtained,String author,String introduction)
            throws SQLException {    //上架图书
        if (selectShelfObtained(shelfObtained.getBook_name()) != null) {
            throw new SQLException("图书已经上架,不能重复上架");
        }
        check.checkException(shelfObtainedMapper.addShelfObtained(shelfObtained), "图书上架失败");
        book.addBook(new Book(
                shelfObtained.getBook_name(),shelfObtained.getCategory_num()+""
                ,author,introduction,true),shelfObtained.getBook_num(),0);
        return shelfObtained;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})       //下架图书
    public List<Book> deleteShelfObtained(String book_name) throws SQLException {
        check.checkObject(selectShelfObtained(book_name), "没有对应的图书");
        check.checkStatus(selectShelfObtainedStatus(book_name), "图书已经下架");
        check.checkException(shelfObtainedMapper.deleteShelfObtained(book_name
                ,findOnlineBookNum(book_name)), "图书下架失败");
        return book.deleteBooks(book_name);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = true)
    public ShelfObtained selectShelfObtained(String book_name) {     //查询是否有对应的图书
        return shelfObtainedMapper.selectShelfObtained(book_name);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = {Exception.class})
    public boolean selectShelfObtainedStatus(String book_name) throws SQLException {     //图书是否已经下架
        return shelfObtainedMapper.selectStatus(book_name) == 0 ? true : false;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Book addBookNum(ShelfObtained shelfObtained) throws SQLException {
        ShelfObtained shelfObtained2=selectShelfObtained(shelfObtained.getBook_name());
        check.checkObject(shelfObtained2, "没有对应的图书");
        check.checkStatus(selectShelfObtainedStatus(shelfObtained.getBook_name()), "图书已经下架");
        check.checkException(shelfObtainedMapper.addBookNum(shelfObtained), "添加图书库存数量失败");
        Book book2=book.findBook(shelfObtained.getBook_name(),null);
        book2.setIdentification(shelfObtained2.getCategory_num()+"");
        book2.setFlag(true);
        return book.addBook(book2,shelfObtained.getBook_num(),shelfObtained2.getBook_num());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public List<Book> deleteBookNum(ShelfObtained shelfObtained) throws SQLException {
        check.checkObject(selectShelfObtained(shelfObtained.getBook_name()), "没有对应的图书");
        check.checkStatus(selectShelfObtainedStatus(shelfObtained.getBook_name()), "图书已经下架");
        check.checkException(findOnlineBookNum(shelfObtained.getBook_name())
                - shelfObtained.getBook_num(), "图书下架的数量不够");
        check.checkException(shelfObtainedMapper.deleteBookNum(shelfObtained), "更改图书库存数量失败");
        return book.deleteBooks(shelfObtained.getBook_name(),shelfObtained.getBook_num());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public ShelfObtained updateStatus(ShelfObtained shelfObtained) throws SQLException {
        check.checkObject(selectShelfObtained(shelfObtained.getBook_name()), "没有对应的图书");
        check.checkException(shelfObtainedMapper.updateStatus(shelfObtained), "更改图书状态失败");
        return shelfObtained;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class},readOnly = true)
    public int findOnlineBookNum(String book_name){
        return shelfObtainedMapper.selectOnlineBookNum(book_name);
    }
}
