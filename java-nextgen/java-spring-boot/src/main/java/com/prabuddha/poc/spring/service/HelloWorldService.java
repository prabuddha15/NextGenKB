package com.prabuddha.poc.spring.service;

import com.prabuddha.poc.spring.aop.LogExecutionTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService {

    @Value("${owner.name:World}")
    private String name;

    @LogExecutionTime
    public String getHelloMessage() {
        return "Hello " + this.name;
    }

}
