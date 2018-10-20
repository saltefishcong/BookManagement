package com.example.bookmanagement.eity;

import com.example.bookmanagement.verification.add;
import com.example.bookmanagement.verification.update;

import javax.validation.constraints.NotNull;

/**
 * auther lwc
 */
public class Debit {  /*  图书借记类 */
    /*  借记类唯一主键 */
    private int id;
    /*  用户唯一标识 */
    @NotNull(groups = {add.class, update.class}, message = "用户标识不能为空")
    private String user_identification;
    /*   书本唯一标识 */
    @NotNull(groups = {add.class, update.class}, message = "书本标识不能为空")
    private String book_identification;
    /*  借书时间 */
    private long time;
    /*  预期的还书时间  */
    private long expected_return_time;
    /*  是否已经还书  */
    @NotNull(groups = {add.class, update.class}, message = "还书标记不能为空")
    private volatile boolean flag;

    public int getId() {
        return id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getUser_identification() {
        return user_identification;
    }

    public void setUser_identification(String user_identification) {
        this.user_identification = user_identification;
    }

    public String getBook_identification() {
        return book_identification;
    }

    public void setBook_identification(String book_identification) {
        this.book_identification = book_identification;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getExpected_return_time() {
        return expected_return_time;
    }

    public void setExpected_return_time(long expected_return_time) {
        this.expected_return_time = expected_return_time;
    }

    public Debit(String user_identification, String book_identification, long time, long expected_return_time, boolean flag) {
        this.user_identification = user_identification;
        this.book_identification = book_identification;
        this.time = time;
        this.expected_return_time = expected_return_time;
        this.flag = flag;
    }

    public Debit() {
    }
}
