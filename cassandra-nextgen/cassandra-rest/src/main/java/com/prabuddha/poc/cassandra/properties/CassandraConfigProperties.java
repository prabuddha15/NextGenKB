package com.prabuddha.poc.cassandra.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//Instead of generate getter and setter method manually we can use lombook
@Component("CassandraConfigProperties")
public class CassandraConfigProperties {

    @Value("${cassandra.contactpoints:127.0.0.1}")
    private String cassandraContactPoints;

//    @Value("${cassandra.username}")
//    private String username;
//
//    @Value("${cassandra.password}")
//    private String password;

    @Value("${cassandra.keyspace:mykeyspace}")
    private String keyspace;

    @Value("${cassandra.port:9042}")
    private String cassandraPort;

//    @Value("${cassandra.pool.core.connections}")
//    private String poolCoreConnection;
//
//    @Value("${cassandra.pool.max.connections}")
//    private String poolMaxConnection;
//
//    @Value("${cassandra.pool.max.requests.local}")
//    private int maxLocalPoolRequests;
//
//    @Value("${cassandra.pool.max.requests.remote}")
//    private int maxRemotePoolRequests;
//
//    @Value("${cassandra.read.timeout.millis}")
//    private int readTimeoutMillis;
//
//    @Value("${cassandra.connect.timeout.millis}")
//    private int connectTimeoutMillis;
//
//    @Value("${cassandra.heartbeat.interval.seconds}")
//    private int heartbeatIntervalSeconds;
//
//    @Value("${cassandra.pool.max.connections.remote}")
//    private int maxPoolConnectionRemote;
//
//    @Value("${cassandra.pool.core.connections.remote}")
//    private int coreRemoteConnection;

//    @Value("${cassandra.policy.retry.writer}")
//    private int cassandraRetryPolicyWrite;

//    @Value("${cassandra.policy.retry.read}")
//    private int cassandraRetryPolicyRead;
//
//    @Value("${cassandra.policy.retry.unavailable}")
//    private int cassandraRetryPolicyUnavailable;
//
//    @Value("${cassandra.policy.retry.write:31}")
//    private int writeRetryAttempts;
//
//    @Value("${cassandra.policy.retry.read:3}")
//    private int readRetryAttempts;
//
//    @Value("${cassandra.policy.retry.unavailable:3}")
//    private int unavailableRetryAttempts;
//
//    @Value("${cassandra.lock.control.consistency.level:ALL}")
//    private String consistencyLevel;
//
//    @Value("${cassandra.lock.control.unsafe:false}")
//    private boolean unsafeLock;

    public String getCassandraContactPoints() {
        return cassandraContactPoints;
    }

    public void setCassandraContactPoints(String cassandraContactPoints) {
        this.cassandraContactPoints = cassandraContactPoints;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(String keyspace) {
        this.keyspace = keyspace;
    }

    public String getCassandraPort() {
        return cassandraPort;
    }

    public void setCassandraPort(String cassandraPort) {
        this.cassandraPort = cassandraPort;
    }

//    public String getPoolCoreConnection() {
//        return poolCoreConnection;
//    }
//
//    public void setPoolCoreConnection(String poolCoreConnection) {
//        this.poolCoreConnection = poolCoreConnection;
//    }
//
//    public String getPoolMaxConnection() {
//        return poolMaxConnection;
//    }
//
//    public void setPoolMaxConnection(String poolMaxConnection) {
//        this.poolMaxConnection = poolMaxConnection;
//    }
//
//    public int getMaxLocalPoolRequests() {
//        return maxLocalPoolRequests;
//    }
//
//    public void setMaxLocalPoolRequests(String maxLocalPoolRequests) {
//        this.maxLocalPoolRequests = Integer.parseInt(maxLocalPoolRequests);
//    }
//
//    public int getMaxRemotePoolRequests() {
//        return maxRemotePoolRequests;
//    }
//
//    public void setMaxRemotePoolRequests(String maxRemotePoolRequests) {
//        this.maxRemotePoolRequests = Integer.parseInt(maxRemotePoolRequests);
//    }
//
//    public int getReadTimeoutMillis() {
//        return readTimeoutMillis;
//    }
//
//    public void setReadTimeoutMillis(String readTimeoutMillis) {
//        this.readTimeoutMillis = Integer.parseInt(readTimeoutMillis);
//    }
//
//    public int getConnectTimeoutMillis() {
//        return connectTimeoutMillis;
//    }
//
//    public void setConnectTimeoutMillis(String connectTimeoutMillis) {
//        this.connectTimeoutMillis = Integer.parseInt(connectTimeoutMillis);
//    }
//
//    public int getHeartbeatIntervalSeconds() {
//        return heartbeatIntervalSeconds;
//    }
//
//    public void setHeartbeatIntervalSeconds(String heartbeatIntervalSeconds) {
//        this.heartbeatIntervalSeconds = Integer.parseInt(heartbeatIntervalSeconds);
//    }
//
//    public int getMaxPoolConnectionRemote() {
//        return maxPoolConnectionRemote;
//    }
//
//    public void setMaxPoolConnectionRemote(String maxPoolConnectionRemote) {
//        this.maxPoolConnectionRemote = Integer.parseInt(maxPoolConnectionRemote);
//    }
//
//    public int getCoreRemoteConnection() {
//        return coreRemoteConnection;
//    }
//
//    public void setCoreRemoteConnection(String coreRemoteConnection) {
//        this.coreRemoteConnection = Integer.parseInt(coreRemoteConnection);
//    }

//    public int getCassandraRetryPolicyWrite() {
//        return cassandraRetryPolicyWrite;
//    }
//
//    public void setCassandraRetryPolicyWrite(String cassandraRetryPolicyWrite) {
//        this.cassandraRetryPolicyWrite = Integer.parseInt(cassandraRetryPolicyWrite);
//    }

//    public int getCassandraRetryPolicyRead() {
//        return cassandraRetryPolicyRead;
//    }
//
//    public void setCassandraRetryPolicyRead(String cassandraRetryPolicyRead) {
//        this.cassandraRetryPolicyRead = Integer.parseInt(cassandraRetryPolicyRead);
//    }
//
//    public int getCassandraRetryPolicyUnavailable() {
//        return cassandraRetryPolicyUnavailable;
//    }
//
//    public void setCassandraRetryPolicyUnavailable(String cassandraRetryPolicyUnavailable) {
//        this.cassandraRetryPolicyUnavailable = Integer.parseInt(cassandraRetryPolicyUnavailable);
//    }
//
//    public int getWriteRetryAttempts() {
//        return writeRetryAttempts;
//    }
//
//    public void setWriteRetryAttempts(String writeRetryAttempts) {
//        this.writeRetryAttempts = Integer.parseInt(writeRetryAttempts);
//    }
//
//    public int getReadRetryAttempts() {
//        return readRetryAttempts;
//    }
//
//    public void setReadRetryAttempts(String readRetryAttempts) {
//        this.readRetryAttempts = Integer.parseInt(readRetryAttempts);
//    }
//
//    public int getUnavailableRetryAttempts() {
//        return unavailableRetryAttempts;
//    }
//
//    public void setUnavailableRetryAttempts(String unavailableRetryAttempts) {
//        this.unavailableRetryAttempts = Integer.parseInt(unavailableRetryAttempts);
//    }
//
//    public String getConsistencyLevel() {
//        return consistencyLevel;
//    }
//
//    public void setConsistencyLevel(String consistencyLevel) {
//        this.consistencyLevel = consistencyLevel;
//    }
//
//    public boolean isUnsafeLock() {
//        return unsafeLock;
//    }
//
//    public void setUnsafeLock(String unsafeLock) {
//        this.unsafeLock = Boolean.parseBoolean(unsafeLock);
//    }
}
