package com.prabuddha.spl.xmlsoccer.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import com.github.pabloo99.xmlsoccer.api.dto.GetAllLeaguesResultDto;
import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.prabuddha.spl.avro.converter.AvroConverter;
import com.prabuddha.spl.model.avro.autogen.LeagueAVRO;
import com.prabuddha.spl.util.SPLConstants;

public class XMLSoccerSPLClientImpl implements XMLSoccerSPLClient {

	private XmlSoccerService xmlSoccerService;
	private AvroConverter avroConverter;
	
	public XMLSoccerSPLClientImpl(XmlSoccerService xmlSoccerService, AvroConverter avroConverter) {
		this.xmlSoccerService = xmlSoccerService;
		this.avroConverter=avroConverter;
	}

	@PostConstruct
	public void init(){
		xmlSoccerService.setApiKey(SPLConstants.API_KEY);
		xmlSoccerService.setServiceUrl(SPLConstants.XML_SOCCER_SERVICE_URL);
	}
	
	@Override
	public List<LeagueAVRO> getAllLeaguesInfoFromXMLSoccerWeb() {
		Collection<GetAllLeaguesResultDto> collAllLeaguesResultDto=xmlSoccerService.getAllLeagues();
        List<GetAllLeaguesResultDto> listAllLeaguesResultDto = new ArrayList<GetAllLeaguesResultDto>(collAllLeaguesResultDto);
        
        List<LeagueAVRO> listAllLeaguesResultAvro = new ArrayList<LeagueAVRO>();
        for(GetAllLeaguesResultDto dto :listAllLeaguesResultDto){
        	listAllLeaguesResultAvro.add(avroConverter.convertLeagueDTOToLeagueAVROObject(dto));
        }
        return listAllLeaguesResultAvro;
	}

}
