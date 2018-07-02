package com.alcohol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.alcohol.mapper")
public class AlcoholApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlcoholApplication.class, args);
    }
}
