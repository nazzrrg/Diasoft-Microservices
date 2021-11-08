package com.example.hw10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(ConsumerChannels.class)
public class Hw10Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw10Application.class, args);
    }

}
