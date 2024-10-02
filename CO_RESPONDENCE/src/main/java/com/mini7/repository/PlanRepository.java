package com.mini7.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini7.model.PlanEntity;

public interface PlanRepository extends JpaRepository<PlanEntity, Serializable>
{

}
