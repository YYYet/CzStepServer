package com.chengzzz.stepservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.chengzzz.stepservice.dao")
@EnableScheduling
public class StepserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StepserviceApplication.class, args);
    }

}
