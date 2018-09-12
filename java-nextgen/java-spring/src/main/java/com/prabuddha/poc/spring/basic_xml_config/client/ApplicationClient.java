package com.prabuddha.poc.spring.basic_xml_config.client;

import com.prabuddha.poc.spring.basic_xml_config.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationClient {

    public static void main(String[] args) {
        //CustomerService service = new CustomerServiceImpl();

        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_basic_xml_config.xml");
        CustomerService service = context.getBean("customerService", CustomerService.class); //Setter Injection

        System.out.println(service.findAll().get(0).getFirstName());
    }
}
