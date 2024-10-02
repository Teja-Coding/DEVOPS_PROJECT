package com.mini5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini5.dto.IncomeBinding;
import com.mini5.service.DataCollectionService;


@RestController
@RequestMapping("/income")

public class IncomeController 
{
	@Autowired
	private DataCollectionService dataCollectionService;
	
	@PostMapping("/save")
	public ResponseEntity<Long> saveIncome(@RequestBody IncomeBinding incomeBinding)
	{
		Long caseNum = dataCollectionService.saveIncomeData(incomeBinding);
		return new ResponseEntity<>(caseNum,HttpStatus.CREATED);
	}
}
