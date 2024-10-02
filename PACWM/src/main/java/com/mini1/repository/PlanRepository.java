package com.mini1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini1.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer>
{
	
}