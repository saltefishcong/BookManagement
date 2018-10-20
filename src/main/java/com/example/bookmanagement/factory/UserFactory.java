package com.example.bookmanagement.factory;

import com.example.bookmanagement.eity.User;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    @Lookup
    public User getUser(){
        return new User();
    }
}
