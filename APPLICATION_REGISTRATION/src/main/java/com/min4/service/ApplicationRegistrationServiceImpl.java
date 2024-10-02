package com.min4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import com.min4.constrants.AppConstrants;
import com.min4.dto.CitizenApplication;
import com.min4.model.CitizenAppEntity;
import com.min4.repository.CitizenAppRepository;

@Service
public class ApplicationRegistrationServiceImpl implements ApplicationRegistrationService 
{
	
	private CitizenAppRepository citizenAppRepository;

	public ApplicationRegistrationServiceImpl(CitizenAppRepository citizenAppRepository)
	{
		this.citizenAppRepository = citizenAppRepository;
	}

//	@Override
//	public Integer createApplication(CitizenApplication cititzenApplication)
//	{
//		String endPointUrl="http://localhost:8480/state/{number}";
//		
//		//RestTemplate
//		
////		RestTemplate tr=new RestTemplate();
////		ResponseEntity<String> repoEntity=tr.getForEntity(endPointUrl, String.class,cititzenApplication.getSsn());
////		String stateName=repoEntity.getBody();
//		
//		//WebClient sync call
//		
//		String stateName=WebClient
//				.create()
//				.get()
//				.uri(endPointUrl,cititzenApplication.getSsn())
//				.retrieve()
//				.bodyToMono(String.class)
//				.block();
//		
//		if(AppConstrants.STATE_NAME.equals(stateName))
//		{
//			CitizenAppEntity citizenAppEntity=new CitizenAppEntity();
//			BeanUtils.copyProperties(cititzenApplication, citizenAppEntity);
//			CitizenAppEntity save = citizenAppRepository.save(citizenAppEntity);
//			return save.getAppId();
//		}
//		
//		return 0;
//	}
	
	public Integer createApplication(CitizenApplication application) {
		// TODO Auto-generated method stub
		//make restcall to ssa-wab api
		
		String endpointUrl="http://localhost:9090/state/{ssn}";
		
		RestTemplate restTemplate= new RestTemplate();
		ResponseEntity<String> forEntity = restTemplate.getForEntity(endpointUrl, String.class,application.getSsn());
		String stateName=forEntity.getBody();
		
		if ("new jersey".equals(stateName)) {
			//create application 
			CitizenAppEntity entity = new CitizenAppEntity();
			BeanUtils.copyProperties(application, entity);
			entity.setStateName(stateName);
			CitizenAppEntity save = citizenAppRepository.save(entity);
			return save.getAppId();
			
		}
		return 0;
	}


	@Override
	public List<CitizenAppEntity> getAllApplications()
	{
		List<CitizenAppEntity> cititzens = citizenAppRepository.findAll();
		List<CitizenAppEntity> citizens=new ArrayList<>();
		for(CitizenAppEntity entity:cititzens)
		{
			CitizenAppEntity citzens=new CitizenAppEntity();
			BeanUtils.copyProperties(entity, citzens);
			citizens.add(citzens);
		}
		return citizens;
	}

	@Override
	public CitizenAppEntity updateCitizen(Integer appId, CitizenApplication citizenApplication)
	{
		CitizenAppEntity citizen = citizenAppRepository.findById(appId).get();
		citizen.setFullName(citizenApplication.getFullName());
		citizen.setEmail(citizenApplication.getEmail());
		citizen.setGender(citizenApplication.getGender());
		citizen.setPhoneNumber(citizenApplication.getPhoneNumber());
		citizen.setSsn(citizenApplication.getSsn());
		citizen.setDob(citizenApplication.getDob());
		return citizenAppRepository.save(citizen);
	}

	@Override
	public Boolean deleteCitizen(Integer appId)
	{
		boolean status = false;
		if(citizenAppRepository.findById(appId).isPresent())
		{
			try {
				citizenAppRepository.deleteById(appId);
				status=true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public CitizenApplication getACitizen(Integer appId)
	{
		Optional<CitizenAppEntity> citizen = citizenAppRepository.findById(appId);
		if(citizen.isPresent())
		{
			CitizenApplication citizenApp=new CitizenApplication();
			CitizenAppEntity citizenAppEntity=citizen.get();
			BeanUtils.copyProperties(citizenAppEntity,citizenApp);
			return citizenApp;
		}
		return null;
	}

}
