package com.mini6.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini6.model.DcCasesEntity;

public interface DcCasesRepository extends JpaRepository<DcCasesEntity, Serializable>
{

}
