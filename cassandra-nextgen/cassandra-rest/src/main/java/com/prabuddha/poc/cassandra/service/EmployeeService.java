package com.prabuddha.poc.cassandra.service;

import com.datastax.driver.mapping.Result;
import com.prabuddha.poc.cassandra.model.EmployeeCW;
import com.prabuddha.poc.cassandra.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeCW getEmployeeDetailsByIdAndName(String employee_id, String name) {
        UUID employee_uuid = UUID.fromString(employee_id);
        //UUID employee_uuid=UUID.fromString("e7b05bc2-a06f-44b1-8c6d-ca4880f91d68");
        System.out.println("UUID Java Value :: " + employee_uuid);
        Result<EmployeeCW> result = employeeRepository.getEmployeeDetailsByIdAndName(employee_uuid, name);
        if (result != null) {
            EmployeeCW employee = result.one();
            return employee;
        }
        return null;
    }
}
