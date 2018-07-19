package com.alcohol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AlcoholApplication {


    public static void main(String[] args) {
        SpringApplication.run(AlcoholApplication.class, args);
    }
}
