package com.prabuddha.poc.spark_rdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class RDDFactoryFromJavaCollection {

	private JavaSparkContext context=null;
	private List<String> countryList = Arrays.asList("India", "USA","Argentina", "Italy");
	private Map<Integer,String> countryListMap = new HashMap<Integer,String>(); 
	
	public RDDFactoryFromJavaCollection() {
		countryListMap.put(1, "India");
		countryListMap.put(2, "USA");
		SparkConf conf = new SparkConf();
		conf.setAppName(RDDFactoryFromJavaCollection.class.getName());
		conf.setMaster("local");
		context = new JavaSparkContext(conf);
	}

	private void shutDownSparkContext() {
		context.close();
		
	}

	private void createRDDFromList() {
		JavaRDD<String> countryRDD=context.parallelize(countryList);
		List<String> countryListFromRDD=countryRDD.collect();
		for(String countryName:countryListFromRDD){
			System.out.println(countryName);
		}
	}
	
	private void createPairRDDFromHashMap(){
		List<Tuple2<Integer, String>> countryTouple2list=fromMapToListTuple2(countryListMap);
		JavaPairRDD<Integer, String> countryPairRdd = context.parallelizePairs(countryTouple2list);
		countryPairRdd.foreach(data -> {
	        System.out.println(data._1() + " --> " + data._2());
	    }); 
	}
	
	
	private  <T1, T2> List<Tuple2<T1, T2>> fromMapToListTuple2(Map<T1, T2> map)
    {
        //list of tuples
        List<Tuple2<T1, T2>> list = new ArrayList<Tuple2<T1, T2>>();

        //loop through all key-value pairs add them to the list
        for(T1 key : map.keySet())
        {
            //get the value
            T2 value = map.get(key);

            //Tuple2 is not like a traditional Java collection, but a single k-v pair;
            Tuple2<T1, T2> tuple2 = new Tuple2<T1, T2>(key, value);

            //populate the list with created tupple2
            list.add(tuple2);
        } // for

        return list;
    } // fromMapToListTuple2
	
	public static void main(String[] args) {
		RDDFactoryFromJavaCollection rddFactory = new RDDFactoryFromJavaCollection();
		rddFactory.createRDDFromList();
		rddFactory.createPairRDDFromHashMap();
		rddFactory.shutDownSparkContext();
	}
}
