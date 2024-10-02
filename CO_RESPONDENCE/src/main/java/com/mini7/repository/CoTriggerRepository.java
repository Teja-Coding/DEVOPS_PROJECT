package com.mini7.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini7.model.CoTriggerEntity;

public interface CoTriggerRepository extends JpaRepository<CoTriggerEntity, Long> 
{
	public List<CoTriggerEntity> findByTrgStatus(String trgStatus);

    Optional<CoTriggerEntity> findByCaseNum(Long caseNum);

}
