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

import com.aurosoft.ecommerce.entity.Company;
import com.aurosoft.ecommerce.service.CompanyService;
import com.aurosoft.ecommerce.util.FileUploadUtil;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	private	CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	
	@GetMapping("/list")
	public  String listcompany(Model m)
	{
		List<Company> list = companyService.listAllCompanys();
		m.addAttribute("list",list);
		
		return "/admin/company-list";
	}
	
	@GetMapping("/new")
	public String showForm(Company company)
	{
		return "/admin/company-add";
	}
	
@PostMapping("/insert")
	
	public String insert(Company company ,@RequestParam("image1") MultipartFile multipartFile) throws IOException
	{
	
	
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
   
	
	
	if(fileName.length() > 3 ) {
	company.setLogo(fileName);
     
	
    Company savedCompany = companyService.insertCompany(company);

    String uploadDir = "./company-photos/" + savedCompany.getId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
    else
    {
 	   company.setLogo("noimg.png");
 	   Company savedCompany = companyService.insertCompany(company);
    }
    
    return "redirect:/company/list";
   
	}

	
	@GetMapping(value="/edit/{id}")
	public String editcompany(@PathVariable int id,Model m)
	{
		
	Company company = companyService.getCompanyById(id);
	m.addAttribute("company", company);
	return "admin/company-edit";
	}
	
	
	@PostMapping(value="/update")
	public String update(Company company ,@RequestParam("image1") MultipartFile multipartFile) throws IOException
	{
	
	
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
   
	
	
	if(fileName.length() > 3 ) {
	company.setLogo(fileName);
     
	
    Company savedCompany = companyService.updateCompany(company);

    String uploadDir = "./company-photos/" + savedCompany.getId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
    else
    {
 	   //company.setImage("noimg.png");
 	   Company savedCompany = companyService.updateCompany(company);
    }
    
    return "redirect:/company/list";
   
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String deletecompany(@PathVariable int id, Model m)
	
	{
		companyService.deleteCompany(id);
		return "redirect:/company/list";
	}
	


	
}
