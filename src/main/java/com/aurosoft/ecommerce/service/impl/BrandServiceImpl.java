package com.aurosoft.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.repository.BrandRepository;
import com.aurosoft.ecommerce.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	BrandRepository brandRepository;
	
	
	@Override
	public List<Brand> listAllBrands() {
		
		return brandRepository.findAll();
	}

	@Override
	public Brand getBrandById(int id) {
		
		return brandRepository.findById(id).orElseThrow();
	}

	@Override
	public Brand insertBrand(Brand brand) {
		
		return brandRepository.save(brand);
	}

	@Override
	public Brand updateBrand(Brand brand) {
		
		return brandRepository.save(brand);
	}

	@Override
	public int deleteBrand(int id) {
		brandRepository.deleteById(id);
		return id;
	}

}
