package com.prabuddha.poc.cassandra.client;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.LatencyAwarePolicy;
import com.datastax.driver.core.policies.RoundRobinPolicy;
import com.datastax.driver.mapping.MappingManager;
import com.prabuddha.poc.cassandra.accessor.CassandraQueryAccessor;
import com.prabuddha.poc.cassandra.properties.CassandraConfigProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;

public class CassandraDataReaderClient {

    private CassandraConfigProperties configProperties;
    private Session session;
    private Cluster cluster;

    public static void main(String[] args) throws Exception{
        CassandraDataReaderClient client=new CassandraDataReaderClient();
        client.execute();
    }

    private void execute()  {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        configProperties = context.getBean("CassandraConfigProperties",CassandraConfigProperties.class);
       session();
        MappingManager mappingManager=new MappingManager(session);
        CassandraQueryAccessor accessor=mappingManager.createAccessor(CassandraQueryAccessor.class);
        ResultSet resultSet=accessor.findContextIdByIdAndDataClassification("56434","INSTRUMENTS");

        Iterator<Row> rowVal= resultSet.iterator();
        while (rowVal!=null && rowVal.hasNext()){
            System.out.println(rowVal.next().getString("CONTEXT_ID"));
        }
        close();
    }

    private void session() {
        session = cluster.connect(configProperties.getKeyspace());
    }

    public Cluster cluster()  {
        cluster=Cluster.builder().addContactPoints(configProperties.getCassandraContactPoints().split(","))
                .withSSL().withCredentials(configProperties.getUsername(),configProperties.getPassword())
                .withProtocolVersion(ProtocolVersion.V4)
                .withQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.QUORUM))
                .withPoolingOptions(createPoolingOptions())
                .withLoadBalancingPolicy(LatencyAwarePolicy.builder(new RoundRobinPolicy()).build()).build();
        cluster.getConfiguration().getSocketOptions().setReadTimeoutMillis(configProperties.getReadTimeoutMillis())
                .setConnectTimeoutMillis(configProperties.getConnectTimeoutMillis());
        cluster.getConfiguration().getPoolingOptions()
                .setHeartbeatIntervalSeconds(configProperties.getHeartbeatIntervalSeconds());
        return cluster;
    }

    private PoolingOptions createPoolingOptions() {
        PoolingOptions poolingOptions=new PoolingOptions();
        poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL,Integer.parseInt(configProperties.getPoolCoreConnection()));
        poolingOptions.setCoreConnectionsPerHost(HostDistance.REMOTE,Integer.parseInt(configProperties.getPoolCoreConnection()));
        poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL,Integer.parseInt(configProperties.getPoolMaxConnection()));
        poolingOptions.setMaxConnectionsPerHost(HostDistance.REMOTE,Integer.parseInt(configProperties.getPoolMaxConnection()));
        poolingOptions.setMaxRequestsPerConnection(HostDistance.LOCAL,configProperties.getMaxLocalPoolRequests());
        poolingOptions.setMaxRequestsPerConnection(HostDistance.REMOTE,configProperties.getMaxLocalPoolRequests());
        return poolingOptions;
    }

    public void close(){
        session.close();
        cluster.close();
    }


}
