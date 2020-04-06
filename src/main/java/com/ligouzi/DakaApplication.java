package com.ligouzi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ligouzi.dao")
public class DakaApplication {

    public static void main(String[] args) {

        SpringApplication.run(DakaApplication.class, args);
    }

}
