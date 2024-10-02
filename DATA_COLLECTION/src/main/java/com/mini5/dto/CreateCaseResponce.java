package com.mini5.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCaseResponce 
{
	private Long caseNum;
	
	private Map<Integer, String> planNames;
}