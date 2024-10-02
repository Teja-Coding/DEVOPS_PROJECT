package com.mini5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeBinding 
{
	private Long caseNum;
	
	private Double empIncome;
	
	private Double propertyIncome;
	
	private Double rentIncome;
}