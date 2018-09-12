package com.prabuddha.poc.spring.basic_java_annotation_config.client;

import com.prabuddha.poc.spring.basic_java_annotation_config.config.ApplicationConfig;
import com.prabuddha.poc.spring.basic_java_annotation_config.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationClient {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ApplicationContext appContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        CustomerService service = appContext.getBean("customerService", CustomerService.class);
        System.out.println(service.findAll().get(0).getFirstName());
    }

}
