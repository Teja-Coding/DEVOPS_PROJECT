package com.mini7.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini7.model.ChildrenEntity;

public interface ChildRepository extends JpaRepository<ChildrenEntity, Serializable> 
{
	public List<ChildrenEntity> findByCaseNum(Long caseNum);
}
