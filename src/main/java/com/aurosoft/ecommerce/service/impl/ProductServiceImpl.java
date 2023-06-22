package com.aurosoft.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.Product;
import com.aurosoft.ecommerce.repository.ProductRepository;
import com.aurosoft.ecommerce.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	
	ProductRepository productRepository;
	
	

	@Override
	
	public List<Product> listAllProducts() {
		
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(int id) {
		
		return productRepository.findById(id).orElseThrow();
	}

	@Override
	public Product insertProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public int deleteProduct(int id) {
		productRepository.deleteById(id);
		return id;
	}


	@Override
	public List<Product> getProductByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public List<Product> getProductByBrand(Brand brand) {
		return productRepository.findByBrand(brand);
	}
	}


