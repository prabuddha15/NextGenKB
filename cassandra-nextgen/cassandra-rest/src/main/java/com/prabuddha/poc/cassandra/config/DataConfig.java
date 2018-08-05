package com.prabuddha.poc.cassandra.config;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.LatencyAwarePolicy;
import com.datastax.driver.core.policies.RoundRobinPolicy;
import com.datastax.driver.mapping.MappingManager;
import com.prabuddha.poc.cassandra.properties.CassandraConfigProperties;
import com.prabuddha.poc.cassandra.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*

org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name
'employeeResourceController': Unsatisfied dependency expressed through field 'employeeService';
nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating
bean with name 'employeeService': Unsatisfied dependency expressed through field 'repository';
nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name
'employeeRepository' defined in class path resource [com/prabuddha/poc/cassandra/config/DataConfig.class]:
Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException:
Failed to instantiate [com.prabuddha.poc.cassandra.repository.EmployeeRepository]: Factory method 'employeeRepository'
 threw exception; nested exception is java.lang.RuntimeException: Error preparing queries for accessor EmployeeRepository

 */

import javax.annotation.PreDestroy;

@Configuration
@EnableAsync
@EnableSwagger2
public class DataConfig extends WebMvcConfigurerAdapter {

    private Session session;
    private Cluster cluster;
    private MappingManager manager;


    private CassandraConfigProperties configProperties = new CassandraConfigProperties();


    @Bean
    public EmployeeRepository employeeRepository() {
        session();
        System.out.println("Session :: " + session);
        MappingManager manager = new MappingManager(session);
        return manager.createAccessor(EmployeeRepository.class);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
        configurer.setTaskExecutor(new SimpleAsyncTaskExecutor("async"));
    }

    @Bean
    public Docket dataServiceApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private void session() {
        //session = cluster.connect(configProperties.getKeyspace());
        session = cluster().connect("mykeyspace");
    }

    public Cluster cluster() {

//        cluster=Cluster.builder().addContactPoints(configProperties.getCassandraContactPoints().split(","))
//                .withProtocolVersion(ProtocolVersion.V4)
//                .withQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.QUORUM))
//                .withPoolingOptions(createPoolingOptions())
//                .withLoadBalancingPolicy(LatencyAwarePolicy.builder(new RoundRobinPolicy()).build()).build();
//        cluster.getConfiguration().getSocketOptions().setReadTimeoutMillis(configProperties.getReadTimeoutMillis())
//                .setConnectTimeoutMillis(configProperties.getConnectTimeoutMillis());
//        cluster.getConfiguration().getPoolingOptions()
//                .setHeartbeatIntervalSeconds(configProperties.getHeartbeatIntervalSeconds());

        try {
            cluster = Cluster.builder()
                    .addContactPoints("127.0.0.1")
                    .withProtocolVersion(ProtocolVersion.V4)
                    .build();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        return cluster;
    }

//    private PoolingOptions createPoolingOptions() {
//        PoolingOptions poolingOptions=new PoolingOptions();
//        poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL,Integer.parseInt(configProperties.getPoolCoreConnection()));
//        poolingOptions.setCoreConnectionsPerHost(HostDistance.REMOTE,Integer.parseInt(configProperties.getPoolCoreConnection()));
//        poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL,Integer.parseInt(configProperties.getPoolMaxConnection()));
//        poolingOptions.setMaxConnectionsPerHost(HostDistance.REMOTE,Integer.parseInt(configProperties.getPoolMaxConnection()));
//        poolingOptions.setMaxRequestsPerConnection(HostDistance.LOCAL,configProperties.getMaxLocalPoolRequests());
//        poolingOptions.setMaxRequestsPerConnection(HostDistance.REMOTE,configProperties.getMaxLocalPoolRequests());
//        return poolingOptions;
//    }

    @PreDestroy
    public void close() {
        session.close();
        cluster.close();
    }
}
