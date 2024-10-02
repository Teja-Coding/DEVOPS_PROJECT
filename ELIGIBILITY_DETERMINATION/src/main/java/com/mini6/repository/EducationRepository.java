package com.mini6.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini6.model.EducationEntity;

public interface EducationRepository extends JpaRepository<EducationEntity, Serializable>
{
	EducationEntity findByCaseNum(Long caseNum);
}
