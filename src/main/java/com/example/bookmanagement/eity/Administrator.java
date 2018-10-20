package com.example.bookmanagement.eity;

import com.example.bookmanagement.verification.Loign;
import com.example.bookmanagement.verification.add;

import javax.validation.constraints.NotNull;

public class Administrator {
    /*  数据库自增ID  */
    private int id;
    /*  管理员唯一标识  */
    @NotNull(groups = {Loign.class}, message = "用户名或者密码不能为空")
    private String identification;
    /*   管理员名字  */
    @NotNull(groups = {add.class}, message = "用户名或者密码不能为空")
    private String name;
    /*  管理员密码  */
    @NotNull(groups = {Loign.class, add.class}, message = "用户名或者密码不能为空")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Administrator(String name, String password, String identification) {
        this.name = name;
        this.password = password;
        this.identification = identification;
    }

    public Administrator() {
    }

    @Override
    public String toString() {
        return getIdentification() + getPassword() + getName();
    }
}
