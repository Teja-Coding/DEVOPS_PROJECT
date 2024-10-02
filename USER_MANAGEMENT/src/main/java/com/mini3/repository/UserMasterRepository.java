package com.mini3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mini3.entity.UserMaster;



public interface UserMasterRepository extends JpaRepository<UserMaster, Integer>
{
	 UserMaster findByEmail(String email);
}