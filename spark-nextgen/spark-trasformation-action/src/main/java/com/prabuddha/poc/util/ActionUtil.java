package com.prabuddha.poc.util;

import java.util.ArrayList;
import java.util.List;

import com.prabuddha.poc.model.FootballerBO;

public class ActionUtil {

	public static List<FootballerBO> populateFootballerDatabase() {
		List<FootballerBO> list=new ArrayList<FootballerBO>();
		FootballerBO footballerBO1=new FootballerBO("Roberto", "Baggio", 31, 29, 13,1000.00);
		FootballerBO footballerBO2=new FootballerBO("Luca", "Toni", 23, 11, 2,1000.00);
		FootballerBO footballerBO3=new FootballerBO("Guisippe", "Signori", 28, 35, 18,1000.00);
		list.add(footballerBO1);
		list.add(footballerBO2);
		list.add(footballerBO3);
		return list;
	}
	
	
	
}
