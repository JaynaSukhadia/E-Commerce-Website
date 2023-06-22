package com.aurosoft.ecommerce.service;

import java.util.List;

import com.aurosoft.ecommerce.entity.Brand;

public interface BrandService {

	
	List<Brand> listAllBrands();
	Brand  getBrandById(int id);
	Brand insertBrand(Brand brand);
	Brand updateBrand(Brand brand);
	int deleteBrand(int id);
}
