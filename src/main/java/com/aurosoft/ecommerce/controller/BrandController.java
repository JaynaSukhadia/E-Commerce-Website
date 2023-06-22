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

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Product;
import com.aurosoft.ecommerce.service.BrandService;
import com.aurosoft.ecommerce.service.ProductService;
import com.aurosoft.ecommerce.util.FileUploadUtil;

@Controller
@RequestMapping("/brand")
public class BrandController {

private	BrandService brandService;
	

	

	public BrandController(BrandService brandService) {
	super();
	this.brandService = brandService;
	
}

	@GetMapping("/list")
	public  String listBrand(Model m)
	{
		List<Brand> list = brandService.listAllBrands();
		m.addAttribute("list",list);
		
		return "/admin/brand-list";
	}
	
	@GetMapping("/new")
	public String showForm(Brand brand, Model m)
	{
		
		
		return "/admin/brand-add";
	}
	
@PostMapping("/insert")
	
	public String insert(Brand brand ,@RequestParam("image1") MultipartFile multipartFile) throws IOException
	{
	
	
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
   
	
	
	if(fileName.length() > 3 ) {
	brand.setImage(fileName);
     
	
    Brand savedBrand = brandService.insertBrand(brand);

    String uploadDir = "./brand-photos/" + savedBrand.getId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
    else
    {
 	  brand.setImage("noimg.png");
 	   Brand savedBrand = brandService.insertBrand(brand);
    }
    
    return "redirect:/brand/list";
   
	}

	
	@GetMapping(value="/edit/{id}")
	public String editBrand(@PathVariable int id,Model m)
	{
		
	Brand brand = brandService.getBrandById(id);
	m.addAttribute("brand", brand);
	
	
	
	
	return "admin/brand-edit";
	}
	
	
	@PostMapping(value="/update")
	
	public String update(Brand brand ,@RequestParam("image1") MultipartFile multipartFile) throws IOException
	{
	
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
   
	
	
	if(fileName.length() > 3 ) {
	brand.setImage(fileName);
     
	
    Brand savedBrand = brandService.updateBrand(brand);

    String uploadDir = "./brand-photos/" + savedBrand.getId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
    else
    {
 	  brand.setImage("noimg.png");
 	   Brand savedBrand = brandService.updateBrand(brand);
    }
    
    return "redirect:/brand/list";
   
	}

	
	
	@GetMapping(value="/delete/{id}")
	public String deleteBrand(@PathVariable int id, Model m)
	
	{
		brandService.deleteBrand(id);
		return "redirect:/brand/list";
	}
}
