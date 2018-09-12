package com.prabuddha.poc.spring.basic_xml_config.service;

import com.prabuddha.poc.spring.basic_xml_config.model.Customer;

import java.util.List;

public interface CustomerService {

    public abstract List<Customer> findAll();
}
