package com.prabuddha.poc.spark_rdd;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RDDJavaFactory {

	private List<String> countryList = Arrays.asList("India", "USA",
			"Argentina", "Italy");

	public static void main(String[] args) {
		RDDJavaFactory rddFactory = new RDDJavaFactory();
		rddFactory.execute();
	}

	private void execute() {
		List<String> countryListFromRDD=createRDDFromList().collect();
		for(String countryName:countryListFromRDD){
			System.out.println(countryName);
		}
	}

	private JavaRDD<String> createRDDFromList() {
		SparkConf conf = new SparkConf();
		conf.setAppName(RDDJavaFactory.class.getName());
		conf.setMaster("local[*]");
		JavaSparkContext context = new JavaSparkContext(conf);
		
		JavaRDD<String> countryRDD=context.parallelize(countryList);
		return countryRDD;
	}
}
