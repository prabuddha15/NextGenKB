package com.prabuddha.spl.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.prabuddha.spl.model.avro.autogen.LeagueAVRO;

public class AvroFileWriter {

	private String fileDirectoryLocation;

	public AvroFileWriter(String fileDirectoryLocation) {
		super();
		this.fileDirectoryLocation = fileDirectoryLocation;
	}

	public void writeLeagueInfoToAvroFile(List<LeagueAVRO> leagueAvroList) {
		File avroOutputFile = new File(fileDirectoryLocation
				+ "/all-leagues.avro");
		try {
			DatumWriter<LeagueAVRO> leagueDatumWriter = new SpecificDatumWriter<LeagueAVRO>(
					LeagueAVRO.class);
			DataFileWriter<LeagueAVRO> dataFileWriter = new DataFileWriter<LeagueAVRO>(
					leagueDatumWriter);
			dataFileWriter.create(leagueAvroList.get(0).getSchema(),
					avroOutputFile);
			for (LeagueAVRO leagueAvro : leagueAvroList) {
				dataFileWriter.append(leagueAvro);
			}
			dataFileWriter.close();
		} catch (IOException e) {
			System.out.println("Error writing Avro");
		}

		// Below line of codes is written for testing purpose. Should be removed
		// later Deserialize sample avro file
		try {
			DatumReader<LeagueAVRO> leagueDatumReader = new SpecificDatumReader(LeagueAVRO.class);
			DataFileReader<LeagueAVRO> dataFileReader = new DataFileReader<LeagueAVRO>(
					avroOutputFile, leagueDatumReader);
			LeagueAVRO league = null;
			while (dataFileReader.hasNext()) {
				league = dataFileReader.next(league);
				System.out.println(league);
			}
		} catch (IOException e) {
			System.out.println("Error reading Avro");
		}

	}
}
