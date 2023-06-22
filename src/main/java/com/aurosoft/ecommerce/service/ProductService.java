package com.aurosoft.ecommerce.service;

import java.util.List;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.Product;



public interface ProductService {

	
	List<Product> listAllProducts();
	Product  getProductById(int id);
	Product insertProduct(Product product);
	Product updateProduct(Product product);
	int deleteProduct(int id);
	
	
	List<Product> getProductByCategory(Category category);
	List<Product> getProductByBrand(Brand brand);


}
