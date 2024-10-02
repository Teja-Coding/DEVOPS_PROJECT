package com.mini6.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mini6.dto.EligResponse;
import com.mini6.service.EligService;

@RestController
public class EdController 
{
	private EligService eligService;

	public EdController(EligService eligService)
	{
		this.eligService = eligService;
	}
	
	//GET
	@GetMapping("/eligibility/{caseNum}")
	ResponseEntity<EligResponse> determaineEligibility(@PathVariable Long caseNum)
	{
		EligResponse determineEligibility = eligService.determineEligibility(caseNum);
		return new ResponseEntity<>(determineEligibility,HttpStatus.OK);
	}
	
}