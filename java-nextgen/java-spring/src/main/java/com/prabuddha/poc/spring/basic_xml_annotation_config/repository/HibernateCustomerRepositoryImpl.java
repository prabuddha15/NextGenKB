package com.prabuddha.poc.spring.basic_xml_annotation_config.repository;

import com.prabuddha.poc.spring.basic_xml_annotation_config.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("customerRepository")
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
