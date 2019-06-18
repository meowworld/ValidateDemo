package com.test.demo;

import listner.DemoApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ValidDemoApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(ValidDemoApplication.class);

        springApplication.addListeners(new DemoApplicationStartedEventListener());

        springApplication.run(args);
    }

}
