package com.prabuddha.poc.spring.basic_java_annotation_config.config;

import com.prabuddha.poc.spring.basic_java_annotation_config.repository.CustomerRepository;
import com.prabuddha.poc.spring.basic_java_annotation_config.repository.HibernateCustomerRepositoryImpl;
import com.prabuddha.poc.spring.basic_java_annotation_config.service.CustomerService;
import com.prabuddha.poc.spring.basic_java_annotation_config.service.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.prabuddha.poc.spring.basic_java_annotation_config"})
public class ApplicationConfig {

    @Bean(name = "customerService")
    public CustomerService getCustomerService() {
        CustomerServiceImpl service = new CustomerServiceImpl();
        service.setCustomerRepository(getCustomerRepository());
        return service;
    }

    @Bean(name = "customerRepository")
    public CustomerRepository getCustomerRepository() {
        return new HibernateCustomerRepositoryImpl();
    }
}
