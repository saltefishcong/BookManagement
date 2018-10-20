package com.example.bookmanagement.eity;

public class Renew {  /*  续借类  */
    /*  数据库自增ID  */
    private int id;
    /*   借记类主键  */
    private int book_debit_id;
    /*   续借书的时间 */
    private long time;
    /*   要续借多少天  */
    private int day;

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Renew(int book_debit_id, long time, int day) {
        this.book_debit_id = book_debit_id;
        this.time = time;
        this.day = day;
    }

    public Renew(){}
}
