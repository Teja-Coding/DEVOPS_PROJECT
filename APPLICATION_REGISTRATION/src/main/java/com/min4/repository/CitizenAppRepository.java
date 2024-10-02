package com.min4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.min4.model.CitizenAppEntity;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Integer> {

}
