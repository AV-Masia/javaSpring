package com.example.sweater.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//        (exclude = {
//        SecurityAutoConfiguration.class
//})
public class BuysellApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuysellApplication.class, args);
        System.out.println("!!!!");
    }
}


