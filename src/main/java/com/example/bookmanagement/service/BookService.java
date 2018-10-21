package com.example.bookmanagement.service;

import com.example.bookmanagement.Mapper.BookMapper;
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
public class BookService {

    @Resource
    private BookMapper bookMapper;

    @Autowired
    private checkService check;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true,rollbackFor = {Exception.class})
    public Book findBook(String identification) throws SQLException{
       return bookMapper.findBook(identification);
    }

   @Transactional(propagation = Propagation.REQUIRED,readOnly = true,rollbackFor = {Exception.class})
    public Book findBookMax(String book_name){
        return bookMapper.findBookMax(book_name);
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true,rollbackFor = {Exception.class})
    public Book findBooker(String book_name) throws SQLException{
        Book book=bookMapper.findBooker(book_name);
        return book;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true,rollbackFor = {Exception.class})
    public List<Book> findBooks(String book_name){
        return bookMapper.findBooks(book_name);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Book addBook(Book book,int book_num,int index) throws SQLException{
        int x=0;
        for(int i=1;i<=book_num;i++){
            ++index;
            Book book2=new Book(book.getName(),book.getIdentification()+index,
                    book.getAuthor(),book.getIntroduction(),book.isFlag());
            x+=bookMapper.addBook(book2);
        }
        check.checkException(x,"添加图书异常");
        return book;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Book deleteBook(String identification) throws SQLException{
        Book book=findBook(identification);
        check.checkObject(book,"没有对应的图书");
        check.checkException(bookMapper.deleteBook(identification),"删除图书失败");
        return book;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public List<Book> deleteBooks(String book_name) throws SQLException{
         List<Book> list=findBooks(book_name);
         check.checkException(bookMapper.deleteBooks(book_name),"删除图书失败");
         return list;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public Book updateStatus(Book book) throws  SQLException{
        check.checkObject(findBook(book.getIdentification()),"查找不到对应的书本");
        check.checkException(bookMapper.updateStatus(book),"更改图书状态失败");
        return book;
    }

}
