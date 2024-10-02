package com.mini1.controller;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mini1.entity.Category;
import com.mini1.entity.Plan;
import com.mini1.service.PlanServices;
import com.mini1.utils.ApplicationConstants;


@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping(value = "/dashboard/admin1")
public class PlanRestController 
{
	//private static final Logger logger=LoggerFactory.getLogger(PlanRestController.class);
	
	private PlanServices planService;


	public PlanRestController(PlanServices planService)
	{
		this.planService = planService;
	}
	
	//GET
	@GetMapping(value = "/categories")
	ResponseEntity<List<Category>> planCategories()
	{
		//logger.info(ApplicationConstants.GET_ALL_CATEGORIES);
		return new ResponseEntity<>(planService.getAllCategorys(),HttpStatus.OK);
	}
	
	//POST
	@PostMapping(value = "/plan")
	ResponseEntity<String> savePlan(@RequestBody Plan plan)
	{
		//logger.info(ApplicationConstants.PLAN_SAVED);
		String msg=ApplicationConstants.EMPTY_MSG;
		boolean savePlan = planService.savePlan(plan);
		if(savePlan) 
			msg=ApplicationConstants.PLAN_SAVED;
		else
		{
			msg=ApplicationConstants.PLAN_NOT_SAVED;
			//logger.error(ApplicationConstants.PLAN_NOT_SAVED);
		}
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	//GET
	@GetMapping(value = "/plans")
	ResponseEntity<List<Plan>> getAllPlans()
	{
		//logger.info(ApplicationConstants.GET_ALL_PLANS);
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans,HttpStatus.OK);
	}
	
	//GET
	@GetMapping(value = "/plan/{planId}")
	ResponseEntity<Plan> getAPlan(@PathVariable Integer planId)
	{
		//logger.info(ApplicationConstants.GET_A_PLAN);
		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan,HttpStatus.OK);
	}
	
	//DELETE
	@DeleteMapping(value = "/plan/{planId}")
	ResponseEntity<String> deletePlanById(@PathVariable Integer planId)
	{
		//logger.warn(ApplicationConstants.DELETING_PLAN);
		String msg=ApplicationConstants.EMPTY_MSG;
		boolean isDeleted = planService.deletePlanById(planId);
		if(isDeleted)
			msg=ApplicationConstants.PLAN_DELETED;
		else
		{
			//logger.error(ApplicationConstants.FAILED_TO_DELETE);
			msg=ApplicationConstants.PLAN_NOT_DELETED;
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	//PUT
	@PutMapping(value = "/update/{planId}")
	ResponseEntity<String> updatePlan(@RequestBody Plan plan,@PathVariable Integer planId)
	{
		//logger.warn(ApplicationConstants.UPDATE_A_PLAN);
		String msg=ApplicationConstants.EMPTY_MSG;
		boolean isUpdated = planService.updatePlan(plan,planId);
		if(isUpdated)
			msg=ApplicationConstants.PLAN_UPDATED;
		else 
		{
			//logger.error(ApplicationConstants.FAILED_TO_UPDATE_A_PLAN);
			msg=ApplicationConstants.PLAN_NOT_UPDATED;
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	//PUT for Status
	@PutMapping(value = "/status/{planId}/{status}")
	ResponseEntity<String> planStatusChange(@PathVariable Integer planId,@PathVariable String status)
	{
		//logger.info(ApplicationConstants.STATUS_ENABLED);
		String msg=ApplicationConstants.EMPTY_MSG;
		boolean isStatusChange = planService.planStatusChange(planId, status);
		if(isStatusChange)
			msg=ApplicationConstants.PLAN_STATUS_CHANGED;
		else
		{
			//logger.error(ApplicationConstants.STATUS_DISABLED);
			msg=ApplicationConstants.PLAN_STATUS_NOT_CHANGED;
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
}
