package com.example.bookmanagement.eity;

import com.example.bookmanagement.verification.add;

import javax.validation.constraints.NotNull;

public class Return {   /* 还书类 */
    /*  数据库自增ID    */
    private int id;
    /*  借记类主键 */
    @NotNull(groups = {add.class}, message = "借阅类标识不能为空")
    private int book_debit_id;
    /*  实际还书时间  */
    private long actual_return_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_debit_id() {
        return book_debit_id;
    }

    public void setBook_debit_id(int book_debit_id) {
        this.book_debit_id = book_debit_id;
    }

    public long getActual_return_time() {
        return actual_return_time;
    }

    public void setActual_return_time(long actual_return_time) {
        this.actual_return_time = actual_return_time;
    }

    public Return(int book_debit_id, long actual_return_time) {
        this.book_debit_id = book_debit_id;
        this.actual_return_time = actual_return_time;
    }

    public Return() {
    }
}
