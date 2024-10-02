package com.mini7.repository;

import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mini7.model.EligDtlsEntity;


public interface EligDtlsRepository extends JpaRepository<EligDtlsEntity, Serializable> 
{
	public EligDtlsEntity findByCaseNum(Long caseNum);
}