package com.mini3.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User 
{
	private String fullName;
	
	private String email;
	
	private Long mobielNumber;
	
	private String gender;
	
	private LocalDate dob;
	
	private Long ssn;
}