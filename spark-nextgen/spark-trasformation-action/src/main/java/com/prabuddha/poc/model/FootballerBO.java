package com.prabuddha.poc.model;

import java.io.Serializable;

public class FootballerBO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
    private String position;
	private int age;
	private int matchPlayed;
	private int goalScored;
	private double wage;


    public FootballerBO(String firstName, String lastName, String position, int age,
			int matchPlayed, int goalScored, double wage) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
        this.position = position;
		this.age = age;
		this.matchPlayed = matchPlayed;
		this.goalScored = goalScored;
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

    public void setPosition(String position) {
        this.position = position;
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

	public int getGoalScored() {
		return goalScored;
	}

	public void setGoalScored(int goalScored) {
		this.goalScored = goalScored;
	}

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public String getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "FootballerBO [firstName=" + firstName + ", lastName="
				+ lastName + ", position=" + position + ", age=" + age
				+ ", matchPlayed=" + matchPlayed + ", goalScored=" + goalScored
				+ ", wage=" + wage + "]";
	}

}
