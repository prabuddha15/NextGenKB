package com.prabuddha.poc.spark_rdd;


import com.prabuddha.poc.model.FootballerBO;
import com.prabuddha.poc.util.ActionUtil;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.*;

import java.util.List;

import org.apache.spark.api.java.function.Function;

/*
 *
 * Implementation SPARK 2.0.0 onwards
 *
 */
public class SparkDataObjectConverter {

    private SparkSession sparkSession;
    private List<FootballerBO> footballerList;

    public SparkDataObjectConverter() {
        sparkSession = SparkSession.builder().master("local").appName("spark-data-converter").getOrCreate();
        footballerList = ActionUtil.populateFootballerDatabase();
    }

    private void convertCollectionToFootballerBODataset() {
        Encoder<FootballerBO> footballerClassEncoder = Encoders.bean(FootballerBO.class);
        Dataset<FootballerBO> footballerDataset = sparkSession.createDataset(footballerList, footballerClassEncoder);
        System.out.println("Show Schema for Footballer Business Object :: ");
        footballerDataset.printSchema();
        System.out.println("Show Footballer Details :: ");
        footballerDataset.show();
    }

    private void convertCollectionToRowDataset() {
        Dataset<Row> footballerRowDataset = sparkSession.createDataFrame(footballerList, FootballerBO.class);
        System.out.println("Show Schema for Row Object :: ");
        footballerRowDataset.printSchema();
        System.out.println("Show Footballer Details in terms of Row Object:: ");
        footballerRowDataset.show();
    }

    private void convertFootballerBODatasetToRDD() {
        Encoder<FootballerBO> footballerClassEncoder = Encoders.bean(FootballerBO.class);
        Dataset<FootballerBO> footballerDataset = sparkSession.createDataset(footballerList, footballerClassEncoder);
        JavaRDD<FootballerBO> footballerJavaRDD = footballerDataset.toJavaRDD();
        System.out.println("Show Footballer Details in terms of FootballerBO Object:: ");
        footballerJavaRDD.foreach(s -> System.out.println(s));
    }

    private void applySQLOperationOnDataset() {
        Dataset<Row> footballerRowDataset = sparkSession.createDataFrame(footballerList, FootballerBO.class);
        footballerRowDataset.createOrReplaceTempView("PLAYER");
        Dataset<Row> teenagerDataset = sparkSession.sql("SELECT firstName, lastName , age FROM PLAYER WHERE age BETWEEN 13 AND 19");
        teenagerDataset.show();
    }

    private void killSpark() {
        sparkSession.stop();
    }

    public static void main(String[] args) {
        SparkDataObjectConverter object = new SparkDataObjectConverter();
        // Convert Footballer List to Spark Dataset containing FootballerBO objects
        object.convertCollectionToFootballerBODataset();
        // Convert Footballer List to Spark Dataset containing Row objects
        object.convertCollectionToRowDataset();
        // Convert Footballer Row Dataset to Java RDD containing FootballerBO objects
        object.convertFootballerBODatasetToRDD();
        //Display player details whose age is below 20.
        object.applySQLOperationOnDataset();
        object.killSpark();
    }


}
