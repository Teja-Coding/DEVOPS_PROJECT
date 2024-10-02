package com.mini5.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenBinding 
{
	private String fullName;
	
	private String email;
	
	private Long phNo;
	
	private  String gender;
	
	private Long ssn;
	
	private LocalDate dob;
}