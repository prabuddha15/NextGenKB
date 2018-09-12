package com.prabuddha.poc.spring.basic_xml_config;

import com.prabuddha.poc.spring.basic_xml_config.service.CustomerService;
import com.prabuddha.poc.spring.basic_xml_config.service.CustomerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_basic_xml_config.xml"})
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
