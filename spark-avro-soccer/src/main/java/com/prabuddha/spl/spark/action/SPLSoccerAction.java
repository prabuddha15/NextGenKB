package com.prabuddha.spl.spark.action;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class SPLSoccerAction extends SparkAction {

	@Override
	protected void processSparkAction() {
		Dataset<Row> df = sparkSession.read().format("com.databricks.spark.avro").load("/home/kyuts/git_workspace/spark-avro-soccer/avro/all-leagues.avro");
		df.show();
	}

}
