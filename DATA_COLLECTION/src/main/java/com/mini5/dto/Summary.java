package com.mini5.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summary
{
	private IncomeBinding income;
	
	private EducationBinding eduaction;
	
	private List<ChildBinding> childerns;
	
	private String palnName;
}
