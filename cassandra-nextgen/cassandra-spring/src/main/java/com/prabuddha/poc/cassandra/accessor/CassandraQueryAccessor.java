package com.prabuddha.poc.cassandra.accessor;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.annotations.Accessor;
import com.prabuddha.poc.cassandra.model.Instrument;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Accessor
public interface CassandraQueryAccessor  {

    @Query("SELECT * FROM INSTRUMENT WHERE CONTEXT_ID = ? AND DATA_CLASSIFICATION = ?")
    ResultSet findContextIdByIdAndDataClassification(String context_id,String dataClassification);
}
