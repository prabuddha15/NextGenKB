package com.prabuddha.spl.xmlsoccer.client;

import java.util.List;

import com.prabuddha.spl.model.avro.autogen.LeagueAVRO;

public interface XMLSoccerSPLClient {
	public List<LeagueAVRO> getAllLeaguesInfoFromXMLSoccerWeb();
}
