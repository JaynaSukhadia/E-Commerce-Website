package com.aurosoft.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.ecommerce.entity.Company;
import com.aurosoft.ecommerce.repository.CompanyRepository;
import com.aurosoft.ecommerce.service.CompanyService;
@Service
public class CompanyServicrImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public List<Company> listAllCompanys() {
		
		return companyRepository.findAll();
	}

	@Override
	public Company getCompanyById(int id) {
		
		return companyRepository.findById(id).orElseThrow();
	}

	@Override
	public Company insertCompany(Company company) {
		
		return companyRepository.save(company);
	}

	@Override
	public Company updateCompany(Company company) {
		
		return companyRepository.save(company);
	}

	@Override
	public int deleteCompany(int id) {
		companyRepository.deleteById(id);
		return id;
	}

}
