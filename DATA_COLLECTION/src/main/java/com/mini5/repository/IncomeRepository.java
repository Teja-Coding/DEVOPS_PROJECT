package com.mini5.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini5.model.IncomeEntity;

public interface IncomeRepository extends JpaRepository<IncomeEntity, Serializable> 
{
	public IncomeEntity findByCaseNum(Long caseNum);
}
