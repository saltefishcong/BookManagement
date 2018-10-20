package com.example.bookmanagement.eity;

public class Overdue {   /*  图书逾期类 */
    /*  数据库自增ID */
    private int id;
    /*  图书借记类主键 */
    private int book_debit_id;
    /*  逾期时间  */
    private long overdue_time;
    /*  逾期费用 */
    private double overdue_cost;
    /*  逾期标志(标志着逾期记录是否被消除了) 0:消除 1:没消除  */
    private volatile boolean flag;

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

    public long getOverdue_time() {
        return overdue_time;
    }

    public void setOverdue_time(long overdue_time) {
        this.overdue_time = overdue_time;
    }

    public double getOverdue_cost() {
        return overdue_cost;
    }

    public void setOverdue_cost(double overdue_cost) {
        this.overdue_cost = overdue_cost;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Overdue(int book_debit_id, long overdue_time, double overdue_cost, boolean flag) {
        this.book_debit_id = book_debit_id;
        this.overdue_time = overdue_time;
        this.overdue_cost = overdue_cost;
        this.flag = flag;
    }

    public Overdue(){}
}
