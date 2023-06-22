package com.aurosoft.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	List<Product> findByCategory(Category category);
	List<Product> findByBrand(Brand brand);
}
