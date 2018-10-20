package com.example.bookmanagement.eity;

import com.example.bookmanagement.verification.add;
import com.example.bookmanagement.verification.status;
import com.example.bookmanagement.verification.update;

import javax.validation.constraints.NotNull;

public class ShelfObtained {  /*  书本上下架类 */
    /*  数据库自增ID */
    private int id;
    /*  书本名称  */
    @NotNull(groups = {add.class, update.class, status.class}, message = "图书名称不能为空")
    private String book_name;
    /*  书本总数量  */
    @NotNull(groups = {add.class, update.class}, message = "图书数量不能为空")
    private int book_num;
    /*   书本在馆数量   */
    private int online_book_num;
    /*  书本类别   */
    @NotNull(groups = {add.class}, message = "图书类别")
    private int category;
    /*   类别编号 */
    @NotNull(groups = {add.class}, message = "ISBN码不能为空")
    private long category_num;
    /*   上下架判断标记  0：上架  1：下架 */
    @NotNull(groups = {add.class, status.class}, message = "操作不能为空")
    private volatile boolean flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getBook_num() {
        return book_num;
    }

    public void setBook_num(int book_num) {
        this.book_num = book_num;
    }

    public int getOnline_book_num() {
        return online_book_num;
    }

    public void setOnline_book_num(int online_book_num) {
        this.online_book_num = online_book_num;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    public boolean isFlag() {
        return flag;
    }

    public long getCategory_num() {
        return category_num;
    }

    public void setCategory_num(long category_num) {
        this.category_num = category_num;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ShelfObtained(String book_name, int book_num, int category, long category_num, boolean flag) {
        this.book_name = book_name;
        this.book_num = book_num;
        this.category = category;
        this.category_num = category_num;
        this.flag = flag;
    }

    public ShelfObtained() {
    }

    @Override
    public String toString() {
        return book_name + "  " + book_num + "  " + online_book_num + "   " + category + "   " + category_num;
    }
}
