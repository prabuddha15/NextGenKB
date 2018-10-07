package com.prabuddha.poc.java8.lambda;

import com.prabuddha.poc.java8.lambda.util.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InnerClassComparator {

    public static void main(String[] args) {
        InnerClassComparator object=new InnerClassComparator();
        object.sortPlayersByFirstNameAndPrint();
        object.sortPlayersByAgeAndPrint();
    }

    private void sortPlayersByFirstNameAndPrint() {
        Comparator<Player> byFirstName = new Comparator<Player>() {
            public int compare(Player o1, Player o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        };
        List<Player> playerList=generatePlayerData();
        Collections.sort(playerList,byFirstName);

        System.out.println("After Sort By First Name");
        for (Player player : playerList) {
            System.out.println(player);
        }

    }

    private void sortPlayersByAgeAndPrint() {
        Comparator<Player> byFirstName = new Comparator<Player>() {
            public int compare(Player o1, Player o2) {
                return new Integer(o1.getAge()).compareTo(new Integer(o2.getAge()));
            }
        };
        List<Player> playerList=generatePlayerData();
        Collections.sort(playerList,byFirstName);

        System.out.println("After Sort By Age");
        for (Player player : playerList) {
            System.out.println(player);
        }

    }

    private List<Player> generatePlayerData(){
        List<Player> playerList=new ArrayList<Player>();
        playerList.add(new Player("Roberto","Baggio",31,211, 2000.00));
        playerList.add(new Player("Barnard","Icra",27,21, 1000.00));
        playerList.add(new Player("Ogba","Kalu",22,89, 1100.00));
        return playerList;
    }

}
