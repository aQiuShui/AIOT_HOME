package com.example.aiot_home;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.aiot_home.mapper")
public class AiotHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiotHomeApplication.class, args);
    }

}
