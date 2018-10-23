package com.example.bookmanagement.factory;

import org.springframework.stereotype.Component;

@Component
public class timeFactory {

    public long getDebitTime(){
        return 5;   //1000 * 60 * 24 * 60 * 60
    }

    public int getRenewDay(){
        return  1;   //30
    }

    public long getRenewTime(int day){
        return day*1000;  //day * 1000 * 60 * 24 * 60
    }

    public int getCost(){
        return  5;
    }
}
