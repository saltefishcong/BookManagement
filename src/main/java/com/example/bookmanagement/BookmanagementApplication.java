package com.example.bookmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BookmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmanagementApplication.class, args);
    }
}
