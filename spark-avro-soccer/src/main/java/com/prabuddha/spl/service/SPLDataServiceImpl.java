package com.prabuddha.spl.service;

import java.util.List;

import com.prabuddha.spl.model.avro.autogen.LeagueAVRO;
import com.prabuddha.spl.util.AvroFileWriter;
import com.prabuddha.spl.xmlsoccer.client.XMLSoccerSPLClient;

public class SPLDataServiceImpl implements SPLDataService {

	private XMLSoccerSPLClient dataClient;
	private AvroFileWriter avroFileWriter;
	

	public void setDataClient(XMLSoccerSPLClient dataClient) {
		this.dataClient = dataClient;
	}

	public void setAvroFileWriter(AvroFileWriter avroFileWriter) {
		this.avroFileWriter = avroFileWriter;
	}

	@Override
	public List<LeagueAVRO> getAllLeagues() {
		List<LeagueAVRO> listAllLeaguesResultAvro=dataClient.getAllLeaguesInfoFromXMLSoccerWeb();
		avroFileWriter.writeLeagueInfoToAvroFile(listAllLeaguesResultAvro);
		return listAllLeaguesResultAvro;
	}

}
