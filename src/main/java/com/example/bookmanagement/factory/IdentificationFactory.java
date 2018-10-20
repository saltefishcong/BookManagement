package com.example.bookmanagement.factory;

import org.springframework.stereotype.Component;

@Component("Identificationfactory")
public class IdentificationFactory {

    private static final String[] user_identification_nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "s", "d", "f", "" +
            "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p"};

    private static final String[] administrator_identification_nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    //private static final  String[] book_category_nums={"01","02","03","04","05","06","07","08","09","10","11","12"};

    public String produceUserIdentification() {
        String Identification = "";
        for (int i = 0; i < 10; i++) {
            int o = (int) (Math.random() * 35);
            Identification += user_identification_nums[o];
        }
        return Identification;
    }

    public String produceAdministratorIdentification() {
        String Identification = "";
        for (int i = 0; i < 10; i++) {
            int o = (int) (Math.random() * 9);
            Identification += administrator_identification_nums[o];
        }
        return Identification;
    }
}
