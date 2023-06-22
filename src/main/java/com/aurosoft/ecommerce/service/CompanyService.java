package com.aurosoft.ecommerce.service;

import java.util.List;

import com.aurosoft.ecommerce.entity.Company;

public interface CompanyService {

	
	List<Company> listAllCompanys();
	Company  getCompanyById(int id);
	Company insertCompany(Company company);
	Company updateCompany(Company company);
	int deleteCompany(int id);
}
