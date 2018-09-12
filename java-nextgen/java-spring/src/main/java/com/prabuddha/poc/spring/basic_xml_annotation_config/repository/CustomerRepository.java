package com.prabuddha.poc.spring.basic_xml_annotation_config.repository;

import com.prabuddha.poc.spring.basic_xml_annotation_config.model.Customer;

import java.util.List;

public interface CustomerRepository {

    public abstract List<Customer> findAll();

}
