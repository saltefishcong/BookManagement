package com.example.bookmanagement.eity;

import com.example.bookmanagement.verification.Loign;
import com.example.bookmanagement.verification.add;
import com.example.bookmanagement.verification.delete;
import com.example.bookmanagement.verification.update;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Scope("prototype")
public class User {
    /*  数据库自增ID  */
    private int id;
    /*  用户呢称 */
    @NotNull(groups = {add.class, update.class}, message = "用户名或者密码不为空")
    private String username;
    /*  用户密码  */
    @NotNull(groups = {add.class, Loign.class, update.class}, message = "用户名或者密码不为空")
    private String password;
    /*  用户性别  */
    @NotNull(groups = {add.class,update.class},message = "性别不为空")
    private String sex;
    /*  用户唯一标识 */
    @NotNull(groups = {delete.class, Loign.class, update.class}, message = "用户id不能为空")
    protected String identification;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public User(String username, String password, String sex, String identification) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.identification = identification;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User() {
    }

    @Override
    public String toString() {
        return super.toString() + "  " + getUsername() + getPassword() + getSex() + getIdentification() + getId();
    }
}
