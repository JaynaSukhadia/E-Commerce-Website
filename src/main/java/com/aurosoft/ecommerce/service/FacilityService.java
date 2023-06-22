package com.aurosoft.ecommerce.service;

import java.util.List;

import com.aurosoft.ecommerce.entity.Facility;

public interface FacilityService {

	
	List<Facility> listAllFacilities();
	Facility  getFacilityById(int id);
	Facility insertFacility(Facility facility);
	Facility updateFacility(Facility facility);
	int deleteFacility(int id);
}
