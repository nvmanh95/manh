package com.epam.ieat.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class BFFApplication {

    public static void main(String[] args) {
        SpringApplication.run(BFFApplication.class, args);
    }

}