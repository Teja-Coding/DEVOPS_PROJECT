package com.min42.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.min42.service.SsnService;

@RestController
@RequestMapping("/state")
public class SsnController 
{
	@Autowired
	private SsnService ssnService;
	
	
	@GetMapping("/{ssn}")
	public ResponseEntity<String> giveState(@PathVariable("ssn") Long ssn){
		
		String state = ssnService.giveState(ssn);
		return new ResponseEntity<>(state,HttpStatus.OK);
		
	}
}
