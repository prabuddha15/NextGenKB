package com.prabuddha.poc.spring.basic_xml_annotation_config.service;

import com.prabuddha.poc.spring.basic_xml_annotation_config.model.Customer;

import java.util.List;

public interface CustomerService {

    public abstract List<Customer> findAll();

}
