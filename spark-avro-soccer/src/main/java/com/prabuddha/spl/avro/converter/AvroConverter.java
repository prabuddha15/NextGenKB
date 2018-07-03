package com.prabuddha.spl.avro.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.github.pabloo99.xmlsoccer.api.dto.GetAllLeaguesResultDto;
import com.prabuddha.spl.model.avro.autogen.LeagueAVRO;

public class AvroConverter {

	public LeagueAVRO convertLeagueDTOToLeagueAVROObject(GetAllLeaguesResultDto leagueDTO){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
		LeagueAVRO leagueAVRO=new LeagueAVRO();
		leagueAVRO.setId(leagueDTO.getId());
		leagueAVRO.setName(leagueDTO.getName());
		leagueAVRO.setCountry(leagueDTO.getCountry());
		leagueAVRO.setHistoricalData(leagueDTO.getHistoricalData());
		leagueAVRO.setFixtures(leagueDTO.getFixtures());
		leagueAVRO.setLivescore(leagueDTO.getLivescore());
		leagueAVRO.setNumberOfMatches(leagueDTO.getNumberOfMatches());
		leagueAVRO.setLatestMatch(dateFormat.format(leagueDTO.getLatestMatch()));
		return leagueAVRO;
	}
}
