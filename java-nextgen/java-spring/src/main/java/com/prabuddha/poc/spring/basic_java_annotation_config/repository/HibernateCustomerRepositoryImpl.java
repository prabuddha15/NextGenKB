package com.prabuddha.poc.spring.basic_java_annotation_config.repository;

import com.prabuddha.poc.spring.basic_java_annotation_config.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class HibernateCustomerRepositoryImpl implements CustomerRepository {

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<Customer>();

        Customer customer = new Customer();
        customer.setFirstName("Prabuddha");
        customer.setLastName("Basu");

        customerList.add(customer);

        return customerList;
    }

}
