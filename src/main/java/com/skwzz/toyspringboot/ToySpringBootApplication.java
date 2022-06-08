package com.skwzz.toyspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ToySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToySpringBootApplication.class, args);
    }

}
