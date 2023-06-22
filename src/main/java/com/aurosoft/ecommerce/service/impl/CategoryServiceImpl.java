package com.aurosoft.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.repository.CategoryRepository;
import com.aurosoft.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
@Autowired
	CategoryRepository catRepository;
	
	
	

	@Override
	public List<Category> listAllCategorys() {
		
		return catRepository.findAll();
	}

	@Override
	public Category getCategoryById(int id) {
		
		return catRepository.findById(id).orElseThrow();
	}

	@Override
	public Category insertCategory(Category category) {
		
		return catRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		
		return catRepository.save(category);
	}

	@Override
	public int deleteCategory(int id) {
		catRepository.deleteById(id);
		return id;
	}

	
	
}
