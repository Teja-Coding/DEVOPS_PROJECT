package com.mini1.service;

import java.util.List;
import com.mini1.entity.Category;
import com.mini1.entity.Plan;

public interface PlanServices 
{
	//Get All Category's
	List<Category> getAllCategorys();
	
	//CreatePlan
	boolean savePlan(Plan plan);
	
	//Get All Plans
	List<Plan> getAllPlans();
	
	//Get A Plan
	Plan getPlanById(Integer planId);
	
	//Update A Plan
	boolean updatePlan(Plan plan,Integer planId);
	
	//Delete A Plan
	boolean deletePlanById(Integer planId);
	
	//softDelete
	boolean planStatusChange(Integer planId,String status);
}