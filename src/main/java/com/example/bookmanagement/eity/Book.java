package com.example.bookmanagement.eity;

import com.example.bookmanagement.verification.Loign;
import com.example.bookmanagement.verification.add;
import com.example.bookmanagement.verification.delete;
import com.example.bookmanagement.verification.update;

import javax.validation.constraints.NotNull;

public class Book {
    /*  数据库自增ID  */
    private int id;
    /*  图书名字  */
    @NotNull(groups = {add.class,delete.class},message = "图书名字不能为空")
    private String name;
    /*   图书标识   */
    @NotNull(groups = {add.class,delete.class,update.class},message = "图书标识不为空")
    private String identification;
    /*  图书作者 */
    @NotNull(groups = {add.class},message = "图书作者不能为空")
    private String author;
    /*  图书简介 */
    @NotNull(groups = {add.class},message = "图书简介不能为空")
    private String introduction;
    /*   图书是否在馆 */
    @NotNull(groups = {add.class,update.class},message = "在馆标识不能为空")
    private volatile boolean flag;

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Book(){}

    public Book(@NotNull(groups = {add.class, delete.class}, message = "图书名字不能为空") String name, String identification, @NotNull(groups = {add.class}, message = "图书作者不能为空") String author, @NotNull(groups = {add.class}, message = "图书简介不能为空") String introduction, boolean flag) {
        this.name = name;
        this.identification = identification;
        this.author = author;
        this.introduction = introduction;
        this.flag = flag;
    }
}
