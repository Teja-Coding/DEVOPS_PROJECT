package com.mini2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mini2.entity.EligibilityDetails;



public interface RepostesRepository extends JpaRepository<EligibilityDetails, Integer>
{
	@Query("SELECT DISTINCT ed.planName FROM EligibilityDetails ed")
	List<String> findAllUniquePlanNames();
	
	@Query("SELECT DISTINCT ed.status FROM EligibilityDetails ed")
	List<String> findAllUniquePlanStatus();
	
	
}
