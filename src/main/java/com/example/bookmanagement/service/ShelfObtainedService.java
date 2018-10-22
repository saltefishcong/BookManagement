package com.example.bookmanagement.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.bookmanagement.Mapper.ShelfObtainedMapper;
import com.example.bookmanagement.eity.Book;
import com.example.bookmanagement.eity.ShelfObtained;
import com.example.bookmanagement.factory.ShelfObtainedFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfObtainedService {

    @Resource
    public ShelfObtainedMapper shelfObtainedMapper;

    @Autowired
    private checkService check;

    @Autowired
    private BookService book;

    @Autowired
    private ShelfObtainedFactory factory;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public ShelfObtained addShelfObtained(ShelfObtained shelfObtained,String author,String introduction)
            throws SQLException {    //上架图书
        ShelfObtained shelfObtained1=selectShelfObtained(shelfObtained.getBook_name());
        if (shelfObtained1!= null && shelfObtained1.isFlag()==false) {
            throw new SQLException("图书已经上架,不能重复上架");
        }
        if(shelfObtained1!= null && shelfObtained1.isFlag()==true){
            shelfObtained1.setFlag(false);
            updateStatus(shelfObtained1);
            List<ShelfObtained> list=new ArrayList<>();
            list.add(shelfObtained);
            addBookNum(list);
            return shelfObtained;
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
    public Book addBookNum(List<ShelfObtained> shelfObtaineds) throws SQLException {
        Book book2=null;
        for(int i=0;i<shelfObtaineds.size();i++){
            ShelfObtained shelfObtained2=selectShelfObtained(shelfObtaineds.get(i).getBook_name());
            check.checkObject(shelfObtained2, "没有对应的图书");
            check.checkStatus(selectShelfObtainedStatus(shelfObtaineds.get(i).getBook_name()), "图书已经下架");
            check.checkException(shelfObtainedMapper.addBookNum(shelfObtaineds.get(i)), "添加图书库存数量失败");
            book2=book.findBookMax(shelfObtaineds.get(i).getBook_name());
            int i_index=book2.getIdentification().indexOf(shelfObtained2.getCategory_num()+"");
            int index=Integer.parseInt(book2.getIdentification().substring(i_index+13,book2.getIdentification().length()));
            book2.setIdentification(shelfObtained2.getCategory_num()+"");
            book2.setFlag(false);
            book.addBook(book2,shelfObtaineds.get(i).getBook_num(), index);
        }
        return book2;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public List<Book> deleteBookNum(String[] identifications) throws SQLException {   //管理员的调用删除方法
        List<Book> list=new ArrayList<>();
        Book book2=null;
        ShelfObtained shelfObtained=factory.getShelfObtained();
        for(int i=0;i<identifications.length;i++){
            book2=book.findBook(identifications[i]);
            check.checkObject(book2,"没有找到相应的图书");
            check.checkStatus(book2.isFlag()==false,"该图书可能不在图书馆");
            check.checkException(findOnlineBookNum(book2.getName())- 1, "请查询清楚图书的信息");
            book.deleteBook(identifications[i]);
            shelfObtained.setBook_name(book2.getName());
            shelfObtained.setBook_num(1);
            check.checkException(shelfObtainedMapper.deleteBookNum(shelfObtained), "更改图书库存数量失败");
            list.add(book2);
        }
        shelfObtained=null;
        return list;
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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class},readOnly = true)
    public List<ShelfObtained> findShelfObtaineds(){
        return shelfObtainedMapper.selectShelfObtaineds();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int deleteBookNumer(String book_name) throws SQLException{   //
        check.checkException(findOnlineBookNum(book_name)-1,"请联系管理员核实图书的信息");
        int x=shelfObtainedMapper.deleteBookNumer(book_name);
        check.checkException(x,"减少图书数量异常");
         return  x;
    }
}
