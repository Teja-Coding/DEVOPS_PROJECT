package com.mini5.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini5.model.DcCaseEntity;

public interface DcCaseRepositroy extends JpaRepository<DcCaseEntity, Serializable> {

}
