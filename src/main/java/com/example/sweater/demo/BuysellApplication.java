package com.example.sweater.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
//        (exclude = {
//        SecurityAutoConfiguration.class
//})
public class BuysellApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuysellApplication.class, args);
    }
}


