package com.mini5.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini5.dto.CreateCaseResponce;
import com.mini5.service.DataCollectionService;


@RestController
@RequestMapping("/create")
public class CreateCaseController 
{
	@Autowired
	private DataCollectionService dataCollectionService;
	
	@GetMapping("/case/{appId}")
	public ResponseEntity<CreateCaseResponce> createCaseNumber(@PathVariable Integer appId)
	{
		Long caseNum = dataCollectionService.loadCaseNum(appId);
		Map<Integer, String> planMap=dataCollectionService.getPlans();
		CreateCaseResponce responce = new CreateCaseResponce();
		responce.setCaseNum(caseNum);
		responce.setPlanNames(planMap);
		return new ResponseEntity<>(responce,HttpStatus.CREATED);
	}
}
