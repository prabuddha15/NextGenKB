package com.prabuddha.poc.spring.basic_java_annotation_config;

import com.prabuddha.poc.spring.basic_java_annotation_config.config.ApplicationConfig;
import com.prabuddha.poc.spring.basic_java_annotation_config.service.CustomerService;
import com.prabuddha.poc.spring.basic_java_annotation_config.service.CustomerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class CustomerServiceTest {

    @Autowired
    @Qualifier("customerService")
    CustomerService customerService;

    @Test
    public void testCustomerServiceAlwaysReturnNotNull() {

        //assert correct type/impl
        assertThat(customerService, instanceOf(CustomerServiceImpl.class));

        //assert true
        assertThat(customerService.findAll(), notNullValue());

    }

}

