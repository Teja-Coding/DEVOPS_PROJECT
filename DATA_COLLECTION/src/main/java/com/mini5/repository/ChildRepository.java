package com.mini5.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini5.model.ChildernEntity;


public interface ChildRepository extends JpaRepository<ChildernEntity , Serializable> 
{
	public List<ChildernEntity> findByCaseNum(Long caseNum);
}
