package com.lin.shiro.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 *@ComponentScan({"com.lin.shiro.core.realm","com.lin.shiro.core.config"})
 */
@MapperScan("com.lin.shiro.core.dao")
@SpringBootApplication
public class BookCityApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookCityApplication.class, args);


    }
}
