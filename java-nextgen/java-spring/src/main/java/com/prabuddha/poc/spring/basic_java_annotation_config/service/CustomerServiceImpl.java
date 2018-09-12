package com.prabuddha.poc.spring.basic_java_annotation_config.service;


import com.prabuddha.poc.spring.basic_java_annotation_config.model.Customer;
import com.prabuddha.poc.spring.basic_java_annotation_config.repository.CustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

}
