package com.mini5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini5.dto.ChildRequestBinding;
import com.mini5.dto.Summary;
import com.mini5.service.DataCollectionService;



@RestController
@RequestMapping("/child")

public class ChildController 
{
	@Autowired
	private DataCollectionService dataCollectionService;
	
	//POST
	@PostMapping("/saveChild")
	public ResponseEntity<Summary> saveChild(@RequestBody ChildRequestBinding childRequestBinding)
	{
		Long caseNum = dataCollectionService.saveChildernData(childRequestBinding);
		Summary summery = dataCollectionService.getSummery(caseNum);
		return new ResponseEntity<>(summery,HttpStatus.CREATED);
	}
}
