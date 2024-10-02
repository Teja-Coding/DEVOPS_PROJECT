package com.mini5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini5.dto.EducationBinding;
import com.mini5.service.DataCollectionService;


@RestController
@RequestMapping("/education")
public class EducationController 
{
	@Autowired
	private DataCollectionService dataCollectionService;
	
	@PostMapping("/save")
	public ResponseEntity<Long> saveEducation(@RequestBody EducationBinding educationBinding)
	{
		Long caseNum = dataCollectionService.saveEducation(educationBinding);
		return new ResponseEntity<>(caseNum,HttpStatus.CREATED);
	}
}
