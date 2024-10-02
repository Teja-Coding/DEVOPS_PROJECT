package com.min4.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenApplication 
{
	private Integer appId;
	
	private String fullName;

	private String email;
	
	private Long phoneNumber;
	
	private String gender;
	
	private Long ssn;
	
	private LocalDate dob;
}