package com.prabuddha.spl.spark.action;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

public abstract class SparkAction extends BaseAction {

	protected SparkSession sparkSession;
	
	protected void preprocess() {
		super.preprocess();
		this.prepareSparkEnv();
	}

	private void prepareSparkEnv() {
		sparkSession = SparkSession
				  .builder().master("local")
				  .appName("spark-avro-soccer")
				  .getOrCreate();
		
	}

	@Override
	protected void process() {
		processSparkAction();
	}

	protected abstract void processSparkAction();

}
