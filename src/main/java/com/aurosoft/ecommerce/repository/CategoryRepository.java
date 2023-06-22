package com.aurosoft.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurosoft.ecommerce.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

	
	
}
