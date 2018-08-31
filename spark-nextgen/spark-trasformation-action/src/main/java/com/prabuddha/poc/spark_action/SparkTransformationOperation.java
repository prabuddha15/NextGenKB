//https://www.alibabacloud.com/forum/read-535
package com.prabuddha.poc.spark_action;

import com.prabuddha.poc.model.FootballerBO;
import com.prabuddha.poc.util.ActionUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;
import org.apache.spark.api.java.Optional;

import java.io.Serializable;
import java.util.*;

public class SparkTransformationOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient JavaSparkContext sc;
    private List<FootballerBO> footballerList;

    public static void main(String[] args) {
        SparkTransformationOperation object = new SparkTransformationOperation();

        //object.map();
        //object.filter();
        //object.flatMap();
        //object.groupByKey();
        //object.reduceByKey();
        //object.sortByKey();
        //object.join();
        object.cogroup();
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

    /*
        map and flatMap are similar, in the sense they take a line from the input RDD and apply a function on it.
        The way they differ is that the function in map returns only one element, while function in flatMap can return
        a list of elements (0 or more) as an iterator.

        Also, the output of the flatMap is flattened. Although the function in flatMap returns a list of elements, the flatMap
        returns an RDD which has all the elements from the list in a flat way (not a list).


        Use Case: We have multiple words per line, and multiple lines, but we end up with a single output array of words. Word Count Program.

     */
    private void flatMap() {
        JavaRDD<FootballerBO> initialPlayerRDD = sc.parallelize(footballerList);

        JavaRDD<String> attributesRDD = initialPlayerRDD.flatMap(new FlatMapFunction<FootballerBO, String>() {

            @Override
            public Iterator<String> call(FootballerBO footballer) throws Exception {
                return Arrays.asList(footballer.toString().split(",")).iterator();
            }
        });

        // Print all players attribute seperately
        attributesRDD.foreach(new VoidFunction<String>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void call(String t) throws Exception {
                System.out.println(t);
            }
        });
    }

    //
    /*
     * This method is responsible to group footballers by their playing position.
     * The groupByKey operator returns JavaPairRDD.
     */
    private void groupByKey() {
        //Create a List<Tuple2<String, FootballerBO>> object from which we can get a JavaPairRDD
        List<Tuple2<String, FootballerBO>> playerListByPosition = new ArrayList<Tuple2<String, FootballerBO>>();
        for (FootballerBO footballer : footballerList) {
            playerListByPosition.add(new Tuple2<String, FootballerBO>(footballer.getPosition(), footballer));
        }

        // Create a JavaPairRDD from playerListByPosition List.
        JavaPairRDD<String, FootballerBO> playerByPositionPairRDD = sc.parallelizePairs(playerListByPosition);

        //Group Players by playing position
        JavaPairRDD<String, Iterable<FootballerBO>> playerByPositionFinalRDD = playerByPositionPairRDD.groupByKey();

        //Print
        playerByPositionFinalRDD.foreach(new VoidFunction<Tuple2<String, Iterable<FootballerBO>>>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void call(Tuple2<String, Iterable<FootballerBO>> t) throws Exception {
                System.out.println("class: " + t._1);
                Iterator<FootballerBO> ite = t._2.iterator();
                while (ite.hasNext()) {
                    System.out.println(ite.next());
                }
                System.out.println("==============================");
            }

        });

    }

    /**
     * The Use case of reduceByKey: Calculate the total goals of every position.
     */
    private void reduceByKey() {
        //Create a List<Tuple2<String, FootballerBO>> object from which we can get a JavaPairRDD
        List<Tuple2<String, FootballerBO>> playerListByPosition = new ArrayList<Tuple2<String, FootballerBO>>();
        for (FootballerBO footballer : footballerList) {
            playerListByPosition.add(new Tuple2<String, FootballerBO>(footballer.getPosition(), footballer));
        }

        // Create a JavaPairRDD from playerListByPosition List.
        JavaPairRDD<String, FootballerBO> playerByPositionPairRDD = sc.parallelizePairs(playerListByPosition);

        // signature of reduceByKey function :: JavaPairRDD<K, V> reduceByKey(Function2<V, V, V> func)
        // Therefore we return a FootballerBO object with adding only total goals. Rest of the fields are populated with N/A value
        JavaPairRDD<String, FootballerBO> totalScoresByEachPosition =
                playerByPositionPairRDD.reduceByKey((footballerBO, footballerBO2) -> new FootballerBO("NA", "NA", footballerBO.getPosition(), 0, 0, footballerBO.getGoalScored() + footballerBO2.getGoalScored(), 0));


        totalScoresByEachPosition.foreach(new VoidFunction<Tuple2<String, FootballerBO>>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void call(Tuple2<String, FootballerBO> t) throws Exception {
                System.out.println("POSITION :: " + t._1 + "  ;  GOAL SCORED :: " + t._2.getGoalScored());
            }

        });

    }

    /**
     * The Use case of sortByKey: Sort by total goals scored by each player.
     */
    private void sortByKey() {
        //Create a List<Tuple2<String, FootballerBO>> object from which we can get a JavaPairRDD
        List<Tuple2<Integer, FootballerBO>> playerListByGoalScored = new ArrayList<Tuple2<Integer, FootballerBO>>();
        for (FootballerBO footballer : footballerList) {
            playerListByGoalScored.add(new Tuple2<Integer, FootballerBO>(footballer.getGoalScored(), footballer));
        }

        // Create a JavaPairRDD from playerListByPosition List.
        JavaPairRDD<Integer, FootballerBO> playerByGoalScoredPairRDD = sc.parallelizePairs(playerListByGoalScored);

        // Sort by total goals scored by each player
        JavaPairRDD<Integer, FootballerBO> sortedByGoalScored = playerByGoalScoredPairRDD.sortByKey(false);

        sortedByGoalScored.foreach(new VoidFunction<Tuple2<Integer, FootballerBO>>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void call(Tuple2<Integer, FootballerBO> t) throws Exception {
                System.out.println("Goal Scored :: " + t._1 + "  ;  Player Details :: " + t._2);
            }

        });

    }

    private void join() {
        //List contains CLUB ID as Key and Player details as value.
        List<Tuple2<Integer, FootballerBO>> footballerList = Arrays.asList(
                new Tuple2<Integer, FootballerBO>(1, new FootballerBO("Roberto", "Baggio", "FWD", 31, 29, 13, 1000.00)),
                new Tuple2<Integer, FootballerBO>(2, new FootballerBO("Luca", "Toni", "FWD", 23, 11, 2, 1000.00)),
                new Tuple2<Integer, FootballerBO>(3, new FootballerBO("Paolo", "Maldini", "DEF", 28, 35, 1, 2000.00)),
                new Tuple2<Integer, FootballerBO>(12, new FootballerBO("Prabuddha", "Basu", "MID", 21, 0, 0, 1.00)));

        //List contains CLUB ID as Key and Club details as value.
        List<Tuple2<Integer, String>> clubList = Arrays.asList(
                new Tuple2<Integer, String>(1, "Juventus"),
                new Tuple2<Integer, String>(2, "Fiorentina"),
                new Tuple2<Integer, String>(3, "AC Milan"),
                new Tuple2<Integer, String>(4, "Napoli"),
                new Tuple2<Integer, String>(5, "AS Roma"));

        // Create a JavaPairRDD from List.
        JavaPairRDD<Integer, FootballerBO> footballerRDD = sc.parallelizePairs(footballerList);
        JavaPairRDD<Integer, String> clubRDD = sc.parallelizePairs(clubList);

        // Join based on key
        // What does that mean? For example, there is a RDD with (1, 1) (1, 2) (1, 3),
        // and another RDD with (1, 4) (2, 1) (2, 2).
        // If it is a cogroup operator, it will be (1,((1,2,3),(4))).
        // But with the join operator, the actual returned values are (1 (1, 4)) (1, (2, 4)) (1, (3, 4)).
        //
        // Join signature:: public <W> JavaPairRDD<K, Tuple2<V, W>> join(JavaPairRDD<K, W> other)


        JavaPairRDD<Integer, Tuple2<FootballerBO, String>> playerClubInfoRDD = footballerRDD.join(clubRDD);

        //Print
        playerClubInfoRDD.foreach(

                new VoidFunction<Tuple2<Integer, Tuple2<FootballerBO, String>>>() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void call(Tuple2<Integer, Tuple2<FootballerBO, String>> t)
                            throws Exception {
                        System.out.println(t._1 + "  " + t._2._1 + "  " + t._2._2);
                        System.out.println("===============================");
                    }

                });

        //Left Outer Join When Club Name is Optional
        JavaPairRDD<Integer, Tuple2<FootballerBO, Optional<String>>> playerInfoOptionalClubLeftRDD = footballerRDD.leftOuterJoin(clubRDD);
        //Print
        System.out.println(playerInfoOptionalClubLeftRDD.collect());


        //Left Outer Join When Footballer Details are Optional
        JavaPairRDD<Integer, Tuple2<String, Optional<FootballerBO>>> clubInfoOptionalplayerLeftRDD = clubRDD.leftOuterJoin(footballerRDD);
        //Print
        System.out.println(clubInfoOptionalplayerLeftRDD.collect());

        //Right Outer Join When Club info is Optional
        JavaPairRDD<Integer, Tuple2<Optional<String>, FootballerBO>> playerInfoOptionalClubRightRDD = clubRDD.rightOuterJoin(footballerRDD);
        //Print
        System.out.println(playerInfoOptionalClubRightRDD.collect());

        //Right Outer Join When Footballer Details are Optional
        JavaPairRDD<Integer, Tuple2<Optional<FootballerBO>, String>> clubInfoOptionalplayerRightRDD = footballerRDD.rightOuterJoin(clubRDD);
        //Print
        System.out.println(clubInfoOptionalplayerRightRDD.collect());

    }

    /**
     * The case of cogroup: Print all the current and previous club names for each Player.  .
     */
    private void cogroup() {

        //List contains CLUB ID as Key and Player details as value.
        List<Tuple2<Integer, FootballerBO>> footballerList = Arrays.asList(
                new Tuple2<Integer, FootballerBO>(1, new FootballerBO("Roberto", "Baggio", "FWD", 31, 29, 13, 1000.00)),
                new Tuple2<Integer, FootballerBO>(2, new FootballerBO("Luca", "Toni", "FWD", 23, 11, 2, 1000.00)),
                new Tuple2<Integer, FootballerBO>(3, new FootballerBO("Paolo", "Maldini", "DEF", 28, 35, 1, 2000.00)));

        //List contains CLUB ID as Key and Club details as value.
        List<Tuple2<Integer, String>> clubList = Arrays.asList(
                new Tuple2<Integer, String>(1, "Juventus"),
                new Tuple2<Integer, String>(1, "Inter Milan"),
                new Tuple2<Integer, String>(1, "AC Milan"),
                new Tuple2<Integer, String>(2, "Fiorentina"),
                new Tuple2<Integer, String>(2, "Bayern Munich"),
                new Tuple2<Integer, String>(3, "AC Milan"));

        // Create a JavaPairRDD from List.
        JavaPairRDD<Integer, FootballerBO> footballerRDD = sc.parallelizePairs(footballerList);
        JavaPairRDD<Integer, String> clubRDD = sc.parallelizePairs(clubList);


        JavaPairRDD<Integer, Tuple2<Iterable<FootballerBO>, Iterable<String>>> clubsByEachPlayer = footballerRDD.cogroup(clubRDD);

        clubsByEachPlayer.foreach(

                new VoidFunction<Tuple2<Integer, Tuple2<Iterable<FootballerBO>, Iterable<String>>>>() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void call(
                            Tuple2<Integer, Tuple2<Iterable<FootballerBO>, Iterable<String>>> t)
                            throws Exception {
                        System.out.println("Player id: " + t._1);
                        System.out.println("Player name: " + t._2._1);
                        System.out.println("Club Name : " + t._2._2);
                        System.out.println("===============================");
                    }

                });

    }

    private void closeSparkContext() {
        sc.close();

    }
}
