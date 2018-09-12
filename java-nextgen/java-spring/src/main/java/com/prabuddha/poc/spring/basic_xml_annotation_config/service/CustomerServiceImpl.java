package com.prabuddha.poc.spring.basic_xml_annotation_config.service;

import com.prabuddha.poc.spring.basic_xml_annotation_config.model.Customer;
import com.prabuddha.poc.spring.basic_xml_annotation_config.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    //@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        System.out.println("We are using setter injection ...");
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
