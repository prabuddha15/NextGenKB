package com.prabuddha.poc.spring.value_annotation.client;

import java.sql.SQLException;

import com.prabuddha.poc.spring.value_annotation.model.DBConnection;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMainClass {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.prabuddha.poc.spring");
        context.refresh();
        System.out.println("Refreshing the spring context");
        DBConnection dbConnection = context.getBean(DBConnection.class);

        dbConnection.printDBConfigs();

        // close the spring context
        context.close();
    }
}
