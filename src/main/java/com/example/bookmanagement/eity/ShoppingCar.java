package com.example.bookmanagement.eity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ShoppingCar {

    private int id;

    private String user_identification;

    private String book_identification;

    public int getId() {
        return id;
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

    public ShoppingCar(String user_identification, String book_identification) {
        this.user_identification = user_identification;
        this.book_identification = book_identification;
    }

    public ShoppingCar() {
    }
}
