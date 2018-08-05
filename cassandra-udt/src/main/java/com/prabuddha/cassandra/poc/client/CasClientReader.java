package com.prabuddha.cassandra.poc.client;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.policies.LatencyAwarePolicy;
import com.datastax.driver.core.policies.RoundRobinPolicy;
import com.datastax.driver.mapping.MappingManager;
//import com.datastax.driver.mapping.UDTMapper;
import com.prabuddha.cassandra.poc.model.Customer;

public class CasClientReader {

	public static final String LOCAL_HOST_IP = "127.0.0.1";
	public static final String CAS_KEYSPACE = "mykeyspace";

	public static void main(String[] args) {
		//List<UDTValue> udtValList = new ArrayList<UDTValue>();
		List<Customer> customerList=new ArrayList<Customer>();
		Cluster cluster = null;
		Session session = null;
		try {

			cluster = Cluster
					.builder()
					.addContactPoints(LOCAL_HOST_IP)
					.withProtocolVersion(ProtocolVersion.NEWEST_SUPPORTED)
					.build();
			
			session = cluster.connect(CAS_KEYSPACE);
			System.out.println("Connection Successful");

			
			ResultSet execute = session.execute("select * from orders");

			for (Row row : execute) {
				UDTValue customerUDT = row.get("customer",UDTValue.class);
				Customer customer=new Customer();
				customer.setId(customerUDT.get(0, String.class));
				customer.setName(customerUDT.get(1, String.class));
				customer.setCouponCode(customerUDT.get(2, String.class));
				customer.setAddress(customerUDT.get(3, String.class));
				customerList.add(customer);
			}
			
			System.out.println("Customer List :: "+customerList);
			

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			cluster.close();
		}
	}

}
