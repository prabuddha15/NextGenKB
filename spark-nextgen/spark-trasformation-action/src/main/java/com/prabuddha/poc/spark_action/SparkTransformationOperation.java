package com.prabuddha.poc.spark_action;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

import com.prabuddha.poc.model.FootballerBO;
import com.prabuddha.poc.util.ActionUtil;

public class SparkTransformationOperation implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient JavaSparkContext sc;
	private List<FootballerBO> footballerList;

	public static void main(String[] args) {
		SparkTransformationOperation object = new SparkTransformationOperation();
		
		object.map();
		object.filter();
		// flatMap();
		// groupByKey();
		// reduceByKey();
		// sortByKey();
		// join();
		// cogroup();
		object.closeSparkContext();

	}

	public SparkTransformationOperation() {
		// Create SparkConf.
		SparkConf conf = new SparkConf().setAppName("spark-action-map")
				.setMaster("local");
		// Create JavaSparkContext.
		sc = new JavaSparkContext(conf);
		// Construct Football player List.
		footballerList = ActionUtil.populateFootballerDatabase();
	}

	/*
	 * This method is responsible to increase the salary of a football player
	 * based on their match performance. if goal scoring ratio is more than 50%,
	 * then double player wage.
	 */
	private void map() {

		// Parallelize sets and create the initial FootballerBO RDD.
		JavaRDD<FootballerBO> initialPlayerRDD = sc.parallelize(footballerList);

		// Use the map operator to revise the wage level based on performance.
		JavaRDD<FootballerBO> revisedFootballerBORDD = initialPlayerRDD.map(

		new Function<FootballerBO, FootballerBO>() {

			private static final long serialVersionUID = 1L;

			@Override
			public FootballerBO call(FootballerBO footballer) throws Exception {

				double goalRatePerMatch = (double) footballer.getGoalScored()
						/ (double) footballer.getMatchPlayed();
				System.out.println("Goal Rate Per Match : " + goalRatePerMatch);
				if (goalRatePerMatch > 0.50) {
					footballer.setWage(footballer.getWage() * 2);
				}
				return footballer;
			}

		});

		// Print the new RDD.
		revisedFootballerBORDD.foreach(new VoidFunction<FootballerBO>() {

			private static final long serialVersionUID = 1L;

			@Override
			public void call(FootballerBO footballer) throws Exception {
				System.out.println(footballer);
			}

		});

		// Disable JavaSparkContext.

	}

	/*
	 * This method is responsible to retrieve the player details whose goal
	 * scoring ratio per match is greater than 50%.
	 */
	private void filter() {

		// Parallelize sets and create the initial FootballerBO RDD.
		JavaRDD<FootballerBO> initialPlayerRDD = sc.parallelize(footballerList);

		// Execute the filter operator to the initial RDD
		JavaRDD<FootballerBO> revisedFootballerBORDD = initialPlayerRDD.filter(

		new Function<FootballerBO, Boolean>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Boolean call(FootballerBO footballer) throws Exception {

				double goalRatePerMatch = (double) footballer.getGoalScored()
						/ (double) footballer.getMatchPlayed();

				if (goalRatePerMatch > 0.50) {
					return true;
				}
				
				return false;

			}

		});

		// Print a new RDD.
		revisedFootballerBORDD.foreach(new VoidFunction<FootballerBO>() {

			private static final long serialVersionUID = 1L;

			@Override
			public void call(FootballerBO footballer) throws Exception {
				System.out.println(footballer);
			}

		});
	}

	private void closeSparkContext() {
		sc.close();

	}
}
