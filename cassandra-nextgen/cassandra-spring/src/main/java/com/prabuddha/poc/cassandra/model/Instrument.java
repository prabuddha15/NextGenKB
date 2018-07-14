package com.prabuddha.poc.cassandra.model;

import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.PartitionKey;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.nio.ByteBuffer;
import java.util.UUID;

@Table("instrument")
@com.datastax.driver.mapping.annotations.Table(name = "instrument")
public class Instrument implements CassandraModel {

    public static final String IDDOMAIN_INSTRUMENTS = "G_SEC_ID";
    public static final String IDOCOMIN_INSTRUMENTS_VENDOR_ID = "INSTR VENDOR_ID";

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, name = "context_id", ordinal = 0)
    @PartitionKey(0)
    @com.datastax.driver.mapping.annotations.Column(name = "context_id")
    private String contextId;

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, name = "data_classification", ordinal = 1)
    @PartitionKey(1)
    @com.datastax.driver.mapping.annotations.Column(name = "data_classification")
    private String dataClassification;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, name = "instrument_id", ordinal = 2)
    @ClusteringColumn(0)
    @com.datastax.driver.mapping.annotations.Column(name = "instrument_id")
    private UDTValue instrumentID;

    @Column("asset_class")
    @com.datastax.driver.mapping.annotations.Column(name = "asset_class")
    private String assetClass;

    @Column("created date")
    @com.datastax.driver.mapping.annotations.Column(name = "created_date")
    private UUID createdDate;

    @Column("instrument_blob")
    @com.datastax.driver.mapping.annotations.Column(name = "instrument_blob")
    private ByteBuffer instrumentBlob;

    @Column("instrument_json")
    @com.datastax.driver.mapping.annotations.Column(name = "instrument_json")
    private String instrumentJson;

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getDataClassification() {
        return dataClassification;
    }

    public void setDataClassification(String dataClassification) {
        this.dataClassification = dataClassification;
    }

    public UDTValue getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(UDTValue instrumentID) {
        this.instrumentID = instrumentID;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public UUID getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(UUID createdDate) {
        this.createdDate = createdDate;
    }

    public ByteBuffer getInstrumentBlob() {
        return instrumentBlob;
    }

    public void setInstrumentBlob(ByteBuffer instrumentBlob) {
        this.instrumentBlob = instrumentBlob;
    }

    public String getInstrumentJson() {
        return instrumentJson;
    }

    public void setInstrumentJson(String instrumentJson) {
        this.instrumentJson = instrumentJson;
    }
}