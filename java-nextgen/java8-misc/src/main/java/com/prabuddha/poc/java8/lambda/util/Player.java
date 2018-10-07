package com.prabuddha.poc.java8.lambda.util;

public class Player {

    private String firstName;
    private String lastName;
    private int age;
    private int matchPlayed;
    private double wage;

    public Player(String firstName, String lastName, int age, int matchPlayed, double wage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.matchPlayed = matchPlayed;
        this.wage = wage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMatchPlayed() {
        return matchPlayed;
    }

    public void setMatchPlayed(int matchPlayed) {
        this.matchPlayed = matchPlayed;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    @Override
    public String toString() {
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", matchPlayed=" + matchPlayed +
                ", wage=" + wage +
                '}';
    }
}
