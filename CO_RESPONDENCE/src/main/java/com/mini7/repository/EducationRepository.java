package com.mini7.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini7.model.EducationEntity;

public interface EducationRepository extends JpaRepository<EducationEntity, Serializable>
{
	public EducationEntity findByCaseNum(Long caseNum);
}
