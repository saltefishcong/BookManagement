package com.example.bookmanagement.factory;

import com.example.bookmanagement.eity.ShelfObtained;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class ShelfObtainedFactory {

    @Lookup
    public ShelfObtained getShelfObtained(){
        return new ShelfObtained();
    }
}
