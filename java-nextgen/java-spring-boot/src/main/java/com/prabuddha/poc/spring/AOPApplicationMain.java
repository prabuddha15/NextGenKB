package com.prabuddha.poc.spring;

import com.prabuddha.poc.spring.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    Notice how we’re using @SpringBootApplication as our primary application configuration class;
    behind the scenes, that’s equivalent to @Configuration, @EnableAutoConfiguration, and @ComponentScan
    together.
 */

@SpringBootApplication
public class AOPApplicationMain implements CommandLineRunner {

    @Autowired
    private HelloWorldService helloWorldService;

    @Override
    public void run(String... args) {
        System.out.println(this.helloWorldService.getHelloMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(AOPApplicationMain.class, args);
    }

}
