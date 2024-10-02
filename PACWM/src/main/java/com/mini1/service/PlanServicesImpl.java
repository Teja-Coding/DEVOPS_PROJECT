package com.mini1.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.mini1.entity.Category;
import com.mini1.entity.Plan;
import com.mini1.repository.CategoryRepository;
import com.mini1.repository.PlanRepository;

@Service
public class PlanServicesImpl implements PlanServices 
{
	private PlanRepository planRepo;
	
	private CategoryRepository categoryRepo;

	public PlanServicesImpl(PlanRepository planRepo, CategoryRepository categoryRepo)
	{
		this.planRepo = planRepo;
		this.categoryRepo = categoryRepo;
	}

	//Get All Category's
	@Override
	public List<Category> getAllCategorys() {
		
		return categoryRepo.findAll();
	}

	//Save Plan
	@Override
	public boolean savePlan(Plan plan) {
		Plan saved = planRepo.save(plan); //(Upsert)
		if(saved.getPlanId()!=null) return true;
		else return false;
		//return saved.getPlanId()!=null?true:false;
		//return planRepo.save(plan).getPlanId()!=null?true:false;
	}

	// View All Plans
	@Override
	public List<Plan> getAllPlans(){
		return planRepo.findAll();
	}
	
	//View One Plan
	@Override
	public Plan getPlanById(Integer planId)
	{
		Optional<Plan> findbyId = planRepo.findById(planId);
		if(findbyId.isPresent()) return findbyId.get();
		else return null;
	}

	//Edit (or) Update Plan
	@Override
	public boolean updatePlan(Plan plan,Integer planId)
	{
		if(planRepo.findById(planId).isPresent())
		{
			planRepo.save(plan); //(Upsert)
		}
		return plan.getPlanId()!=null;
	}

	//Delete Plan
	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status=false;
		if(planRepo.findById(planId).isPresent())
		{
		try {
			planRepo.deleteById(planId);
			status=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return status;
	}

	//Change ActiveSwitch Status
	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findbyId = planRepo.findById(planId);
		if(findbyId.isPresent())
		{
			Plan plan=findbyId.get();
			plan.setActiveSwitch(status);
			planRepo.save(plan);
			return true;
		}
		return false;
	}
}