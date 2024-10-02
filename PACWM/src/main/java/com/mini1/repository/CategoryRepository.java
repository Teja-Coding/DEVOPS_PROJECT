package com.mini1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini1.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>
{
	
}