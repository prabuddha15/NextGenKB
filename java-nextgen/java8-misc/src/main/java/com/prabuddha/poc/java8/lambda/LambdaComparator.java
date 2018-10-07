package com.prabuddha.poc.java8.lambda;

import com.prabuddha.poc.java8.lambda.util.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaComparator {

    public static void main(String[] args) {
        LambdaComparator object=new LambdaComparator();
        System.out.println("After Sort By First Name ::");
        object.sortPlayersByFirstNameAndPrint();
        System.out.println("After Sort By Age ::");
        object.sortPlayersByAgeAndPrint();
    }

    private void sortPlayersByFirstNameAndPrint() {
        List<Player> playerList=generatePlayerData();
        Comparator<Player> byFirstName = (Player o1, Player o2) -> o1.getFirstName().compareTo(o2.getFirstName());
        Collections.sort(playerList,byFirstName);
        playerList.forEach(System.out::println);
    }

    private void sortPlayersByAgeAndPrint() {
        List<Player> playerList=generatePlayerData();
        Comparator<Player> byAge = (Player o1, Player o2) -> new Integer(o1.getAge()).compareTo(new Integer(o2.getAge()));
        Collections.sort(playerList,byAge);
        playerList.forEach(System.out::println);
    }

    private List<Player> generatePlayerData(){
        List<Player> playerList=new ArrayList<Player>();
        playerList.add(new Player("Roberto","Baggio",31,211, 2000.00));
        playerList.add(new Player("Barnard","Icra",27,21, 1000.00));
        playerList.add(new Player("Ogba","Kalu",22,89, 1100.00));
        return playerList;
    }
}
