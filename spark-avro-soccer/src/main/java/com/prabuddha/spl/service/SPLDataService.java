package com.prabuddha.spl.service;

import java.util.List;

import com.prabuddha.spl.model.avro.autogen.LeagueAVRO;

public interface SPLDataService {
	public List<LeagueAVRO> getAllLeagues();
}
