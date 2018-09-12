package com.prabuddha.poc.spring.basic_xml_config.service;

import com.prabuddha.poc.spring.basic_xml_config.model.Customer;
import com.prabuddha.poc.spring.basic_xml_config.repository.CustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    // Below way to create instance needs to be replaced by Spring
    private CustomerRepository customerRepository;

    public CustomerServiceImpl() {
    }

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
