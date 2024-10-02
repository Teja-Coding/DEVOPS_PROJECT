package com.mini5.service;

import java.util.Map;
import com.mini5.dto.ChildRequestBinding;
import com.mini5.dto.EducationBinding;
import com.mini5.dto.IncomeBinding;
import com.mini5.dto.PlanSelctionBinding;
import com.mini5.dto.Summary;

public interface DataCollectionService 
{
	public Long loadCaseNum(Integer appId);
	
	public Map<Integer, String> getPlans();
	
	public Long savePlanSelection(PlanSelctionBinding palSelctionBinding);
	
	public Long saveIncomeData(IncomeBinding incomeBinding);
	
	public Long saveEducation(EducationBinding educationBinding);
	
	public Long saveChildernData(ChildRequestBinding childRequestBinding);
	
	public Summary getSummery(Long caseNum);
}