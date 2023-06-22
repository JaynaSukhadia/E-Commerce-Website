package com.aurosoft.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aurosoft.ecommerce.entity.Facility;
import com.aurosoft.ecommerce.service.FacilityService;
import com.aurosoft.ecommerce.util.FileUploadUtil;

@Controller
@RequestMapping("/facility")
public class FacilityController {

	
	private FacilityService facilityService;

	public FacilityController(FacilityService facilityService) {
		super();
		this.facilityService = facilityService;
	}
	
	
	@GetMapping("/list")
	public  String listFacility(Model m)
	{
		List<Facility> list = facilityService.listAllFacilities();
		m.addAttribute("list",list);
		
		return "/admin/facility-list";
	}
	
	@GetMapping("/new")
	public String showForm(Facility facility)
	{
		return "/admin/facility-add";
	}
	
@PostMapping("/insert")
	
	public String insert(Facility facility ,@RequestParam("image1") MultipartFile multipartFile) throws IOException
	{
	
	
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
   
	
	
	if(fileName.length() > 3 ) {
	facility.setIcon(fileName);
     
	
    Facility savedFacility = facilityService.insertFacility(facility);

    String uploadDir = "./service-photos/" + savedFacility.getId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
    else
    {
 	   facility.setIcon("noimg.png");
 	   Facility savedFacility = facilityService.insertFacility(facility);
    }
    
    return "redirect:/facility/list";
   
	}

	
	@GetMapping(value="/edit/{id}")
	public String editFacility(@PathVariable int id,Model m)
	{
		
	Facility facility = facilityService.getFacilityById(id);
	m.addAttribute("Facility", facility);
	
	return "admin/facility-edit";
	}
	
	
	@PostMapping(value="/update")
	public String update(Facility facility ,@RequestParam("image1") MultipartFile multipartFile) throws IOException
	{
	
	
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
   
	
	
	if(fileName.length() > 3 ) {
	facility.setIcon(fileName);
     
	
    Facility savedFacility = facilityService.updateFacility(facility);

    String uploadDir = "./service-photos/" + savedFacility.getId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
    else
    {
 	   //Facility.setImage("noimg.png");
 	   Facility savedFacility = facilityService.updateFacility(facility);
    }
    
    return "redirect:/facility/list";
   
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String deleteFacility(@PathVariable int id, Model m)
	
	{
		facilityService.deleteFacility(id);
		return "redirect:/facility/list";
	}
	
}
