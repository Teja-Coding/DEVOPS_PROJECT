package com.mini5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini5.dto.PlanSelctionBinding;
import com.mini5.service.DataCollectionService;


@RestController
@RequestMapping("/plan")

public class PlanSelectionController 
{
	@Autowired
	private DataCollectionService dataCollectionService;
	
	@PostMapping("/selection")
	public ResponseEntity<Long> selectPlan(@RequestBody PlanSelctionBinding planSelctionBinding)
	{
		Long caseNum = dataCollectionService.savePlanSelection(planSelctionBinding);
		return new ResponseEntity<>(caseNum,HttpStatus.CREATED);
	}
}
