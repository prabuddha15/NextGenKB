package com.prabuddha.poc.cassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, JmxAutoConfiguration.class})
public class CASBootApplication {
    public static void main(String[] args) {
        System.out.println("Application started .........");
        SpringApplication.run(CASBootApplication.class, args);
    }
}
