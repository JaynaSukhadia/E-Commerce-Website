package com.aurosoft.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurosoft.ecommerce.entity.Facility;
import com.aurosoft.ecommerce.repository.FacilityRepository;
import com.aurosoft.ecommerce.service.FacilityService;
@Service



public class FacilityServiceImpl implements FacilityService{

	@Autowired
	FacilityRepository facilityRepository;
	

	@Override
	public List<Facility> listAllFacilities() {
		
		return facilityRepository.findAll();
	}

	@Override
	public Facility getFacilityById(int id) {
		
		return facilityRepository.findById(id).orElseThrow();
	}

	@Override
	public Facility insertFacility(Facility facility) {
		
		return facilityRepository.save(facility);
	}

	@Override
	public Facility updateFacility(Facility facility) {
		
		return facilityRepository.save(facility);
	}

	@Override
	public int deleteFacility(int id) {
		facilityRepository.deleteById(id);
		return id;
	}

}
