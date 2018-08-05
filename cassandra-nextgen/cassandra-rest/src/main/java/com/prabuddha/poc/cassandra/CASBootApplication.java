package com.prabuddha.poc.cassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@ComponentScan(value = "com.prabuddha.poc.cassandra")
@EnableCassandraRepositories(value = {"com.prabuddha.poc.cassandra.model", "com.prabuddha.poc.cassandra.repository"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, JmxAutoConfiguration.class})
public class CASBootApplication {
    public static void main(String[] args) {
        System.out.println("Application started .........");
        SpringApplication.run(CASBootApplication.class, args);
    }
}
