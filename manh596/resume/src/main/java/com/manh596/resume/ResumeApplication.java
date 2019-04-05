package com.manh596.resume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.manh596.common")
public class ResumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication.class, args);
    }
}