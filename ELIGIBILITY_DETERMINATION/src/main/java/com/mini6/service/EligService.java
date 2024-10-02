package com.mini6.service;

import com.mini6.dto.EligResponse;

public interface EligService 
{
	public EligResponse determineEligibility(Long caseNum);
}