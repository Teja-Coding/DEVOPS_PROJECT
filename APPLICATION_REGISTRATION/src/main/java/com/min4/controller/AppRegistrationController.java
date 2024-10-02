package com.min4.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.min4.constrants.AppConstrants;
import com.min4.dto.CitizenApplication;
import com.min4.model.CitizenAppEntity;
import com.min4.service.ApplicationRegistrationService;


@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/app")
public class AppRegistrationController 
{
	private ApplicationRegistrationService applicationRegistrationService;

	public AppRegistrationController(ApplicationRegistrationService applicationRegistrationService)
	{
		this.applicationRegistrationService = applicationRegistrationService;
	}
	
//	//POST
//	@PostMapping("/register")
//	ResponseEntity<String> createApplicationRequest( @RequestBody CitizenApplication citizenApplication)
//	{
//		Integer appId = applicationRegistrationService.createApplication(citizenApplication);
//		if(appId>0)
//			return new ResponseEntity<>(AppConstrants.APPLICATION_CREATED_SUCCESSFULLY+appId,HttpStatus.CREATED);
//		else
//			return new ResponseEntity<>(AppConstrants.INAVALID_SSN,HttpStatus.BAD_REQUEST);
//	}
	

	@PostMapping("/register")
	public ResponseEntity<String> createCitizenApplication(@RequestBody CitizenApplication application) {
		Integer appId = applicationRegistrationService.createApplication(application);

		if (appId > 0) {
			return new ResponseEntity<>("Application created with AppId: " + appId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invaild ssn", HttpStatus.BAD_REQUEST);
		}

	}
	
	//GET
	@GetMapping("/all")
	ResponseEntity<List<CitizenAppEntity>> getAllApplications()
	{
		List<CitizenAppEntity> allApplications = applicationRegistrationService.getAllApplications();
		return new ResponseEntity<>(allApplications,HttpStatus.OK);
	}
	
	//GET
	@GetMapping("/citizen/{appId}")
	ResponseEntity<CitizenApplication> getAapplication(@PathVariable Integer appId)
	{
		CitizenApplication Citizen = applicationRegistrationService.getACitizen(appId);
		return new ResponseEntity<>(Citizen,HttpStatus.OK);
	}
	
	//PUT
	@PutMapping("/update/{appId}")
	ResponseEntity<CitizenAppEntity> updateCititzen(@PathVariable Integer appId,@RequestBody CitizenApplication citizenApplication)
	{
		CitizenAppEntity updateCitizen = applicationRegistrationService.updateCitizen(appId, citizenApplication);
		return new ResponseEntity<>(updateCitizen,HttpStatus.OK);
	}
	
	//DELETE
	@DeleteMapping("/delete/{appId}")
	ResponseEntity<String> deleteCitizen(@PathVariable Integer appId)
	{
		String msg=AppConstrants.EMPTY_STRING;
		Boolean isDeleted = applicationRegistrationService.deleteCitizen(appId);
		if(isDeleted)
			msg=AppConstrants.CITIZEN_DELETED;
		else
			msg=AppConstrants.CITIZEN_NOT_DELETED;
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
}
