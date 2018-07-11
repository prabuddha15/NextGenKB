package com.prabuddha.poc.avro.util;

import java.util.ArrayList;
import java.util.List;

import com.prabuddha.poc.avro.autogen.BdPerson;

public class PersonDataPopulator {
	public static List<BdPerson> populatePersonDataList() {

		List<BdPerson> list = new ArrayList<BdPerson>();
		BdPerson person = new BdPerson();
		person.setId(1);
		person.setUsername("mrscarter");
		person.setFirstName("Beyonce");
		person.setLastName("Knowles-Carter");
		person.setBirthdate("1981-09-04");
		person.setJoinDate("2016-01-01");
		person.setPreviousLogins(10000);

		BdPerson anotherPerson = new BdPerson();
		anotherPerson.setId(2);
		anotherPerson.setUsername("jayz");
		anotherPerson.setFirstName("Shawn");
		anotherPerson.setMiddleName("Corey");
		anotherPerson.setLastName("Carter");
		anotherPerson.setBirthdate("1969-12-04");
		anotherPerson.setJoinDate("2016-01-01");
		anotherPerson.setPreviousLogins(20000);

		list.add(person);
		list.add(anotherPerson);
		return list;
	}
}
