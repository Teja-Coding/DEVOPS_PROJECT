package com.min4.service;

import java.util.List;

import com.min4.dto.CitizenApplication;
import com.min4.model.CitizenAppEntity;


public interface ApplicationRegistrationService 
{
//	public Integer createApplication(CitizenApplication cititzenApplication);
	
	public Integer createApplication(CitizenApplication application);
	
	public List<CitizenAppEntity> getAllApplications();
	
	public CitizenAppEntity updateCitizen(Integer appId,CitizenApplication citizenApplication);
	
	public Boolean deleteCitizen(Integer appId);
	
	public CitizenApplication getACitizen(Integer appId);
}