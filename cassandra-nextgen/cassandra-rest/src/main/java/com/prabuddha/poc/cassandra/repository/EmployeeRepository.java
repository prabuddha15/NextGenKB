package com.prabuddha.poc.cassandra.repository;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.prabuddha.poc.cassandra.model.EmployeeCW;

import java.util.UUID;

@Accessor
public interface EmployeeRepository extends CassandraRepository {
    @Query("SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID= :employee_id and NAME= :name")
    public Result<EmployeeCW> getEmployeeDetailsByIdAndName(UUID employee_id, String name);
}
