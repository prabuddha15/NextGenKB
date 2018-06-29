package com.prabuddha.poc.avro.service;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.prabuddha.poc.avro.autogen.BdPerson;

public class AvroFileWriter {

	private void execute() {

		// serialize Data To Disk

		BdPerson person = populatePersonData();
		BdPerson anotherPerson = populateAnotherPersonData();
		File avroOutputFile = new File("bdperson-test.avro");
		try {
			DatumWriter<BdPerson> bdPersonDatumWriter = new SpecificDatumWriter<BdPerson>(
					BdPerson.class);
			DataFileWriter<BdPerson> dataFileWriter = new DataFileWriter<BdPerson>(
					bdPersonDatumWriter);
			dataFileWriter.create(person.getSchema(), avroOutputFile);
			dataFileWriter.append(person);
			dataFileWriter.append(anotherPerson);
			dataFileWriter.close();
		} catch (IOException e) {
			System.out.println("Error writing Avro");
		}

		// Deserialize sample avro file
		try {
			DatumReader<BdPerson> bdPersonDatumReader = new SpecificDatumReader(BdPerson.class);
			DataFileReader<BdPerson> dataFileReader = new DataFileReader<BdPerson>(avroOutputFile, bdPersonDatumReader);
			BdPerson p = null;
			while (dataFileReader.hasNext()) {
				p = dataFileReader.next(p);
				System.out.println(p);
			}
		} catch (IOException e) {
			System.out.println("Error reading Avro");
		}

	}

	private BdPerson populateAnotherPersonData() {
		BdPerson anotherPerson = new BdPerson();
		anotherPerson.setId(2);
		anotherPerson.setUsername("jayz");
		anotherPerson.setFirstName("Shawn");
		anotherPerson.setMiddleName("Corey");
		anotherPerson.setLastName("Carter");
		anotherPerson.setBirthdate("1969-12-04");
		anotherPerson.setJoinDate("2016-01-01");
		anotherPerson.setPreviousLogins(20000);

		return anotherPerson;
	}

	private BdPerson populatePersonData() {
		BdPerson person = new BdPerson();
		person.setId(1);
		person.setUsername("mrscarter");
		person.setFirstName("Beyonce");
		person.setLastName("Knowles-Carter");
		person.setBirthdate("1981-09-04");
		person.setJoinDate("2016-01-01");
		person.setPreviousLogins(10000);
		return person;
	}

	public static void main(String[] args) {
		AvroFileWriter writer = new AvroFileWriter();
		writer.execute();
	}
}
