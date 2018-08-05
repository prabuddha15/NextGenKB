package com.prabuddha.poc.cassandra.model;

import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Table(keyspace = "mykeyspace", name = "employee")
//@NoArgsConstructor
//@ToString
//@Data
public class EmployeeCW implements CassandraModel {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, name = "employee_id", ordinal = 0)
    @PartitionKey(0)
    @com.datastax.driver.mapping.annotations.Column(name = "employee_id")
    private UUID employee_id;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, name = "name", ordinal = 1)
    @ClusteringColumn(0)
    @com.datastax.driver.mapping.annotations.Column(name = "name")
    private String name;

    @Column("age")
    @com.datastax.driver.mapping.annotations.Column(name = "age")
    private int age;

    @Column("prev_employment_list")
    @com.datastax.driver.mapping.annotations.Column(name = "prev_employment_list")
    private List<UDTValue> prevEmploymentList;

    @Column("skill_exp")
    @com.datastax.driver.mapping.annotations.Column(name = "skill_exp")
    private Map<String, Integer> skillExp;

    public EmployeeCW() {
    }

    public EmployeeCW(UUID employee_id, String name, int age, List<UDTValue> prevEmploymentList, Map<String, Integer> skillExp) {
        this.employee_id = employee_id;
        this.name = name;
        this.age = age;
        this.prevEmploymentList = prevEmploymentList;
        this.skillExp = skillExp;
    }

    public UUID getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(UUID employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<UDTValue> getPrevEmploymentList() {
        return prevEmploymentList;
    }

    public void setPrevEmploymentList(List<UDTValue> prevEmploymentList) {
        this.prevEmploymentList = prevEmploymentList;
    }

    public Map<String, Integer> getSkillExp() {
        return skillExp;
    }

    public void setSkillExp(Map<String, Integer> skillExp) {
        this.skillExp = skillExp;
    }
}
