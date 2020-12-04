package com.at.zcw.mqtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MqTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqTestApplication.class, args);
    }

}
