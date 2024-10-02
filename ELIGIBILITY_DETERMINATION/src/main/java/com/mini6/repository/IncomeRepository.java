package com.mini6.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini6.model.IncomeEntity;

public interface IncomeRepository extends JpaRepository<IncomeEntity, Serializable>
{
	public IncomeEntity findByCaseNum(Long caseNum);
}
