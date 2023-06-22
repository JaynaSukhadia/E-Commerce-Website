package com.aurosoft.ecommerce.service;

import java.util.List;

import com.aurosoft.ecommerce.entity.Category;



public interface CategoryService {

	
	List<Category> listAllCategorys();
	Category  getCategoryById(int id);
	Category insertCategory(Category category);
	Category updateCategory(Category category);
	int deleteCategory(int id);
}
