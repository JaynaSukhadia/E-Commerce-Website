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

import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.service.CategoryService;
import com.aurosoft.ecommerce.util.FileUploadUtil;


@Controller
@RequestMapping("/category")
public class CategoryController {
	
private	CategoryService categoryService;



	public CategoryController(CategoryService categoryService) {
	super();
	this.categoryService = categoryService;
}

	@GetMapping("/list")
	public  String listcategory(Model m)
	{
		List<Category> list = categoryService.listAllCategorys();
		m.addAttribute("list",list);
		
		return "/admin/category-list";
	}
	
	@GetMapping("/new")
	public String showForm(Category category)
	{
		return "/admin/category-add";
	}
	
@PostMapping("/insert")
	
	public String insert(Category category ,@RequestParam("image1") MultipartFile multipartFile) throws IOException
	{
	
	
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
   
	
	
	if(fileName.length() > 3 ) {
	category.setImage(fileName);
     
	
    Category savedCategory = categoryService.insertCategory(category);

    String uploadDir = "./category-photos/" + savedCategory.getId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
    else
    {
 	   category.setImage("noimg.png");
 	   Category savedCategory = categoryService.insertCategory(category);
    }
    
    return "redirect:/category/list";
   
	}

	
	@GetMapping(value="/edit/{id}")
	public String editcategory(@PathVariable int id,Model m)
	{
		
	Category category = categoryService.getCategoryById(id);
	m.addAttribute("category", category);
	return "admin/category-edit";
	}
	
	
	@PostMapping(value="/update")
	public String update(Category category ,@RequestParam("image1") MultipartFile multipartFile) throws IOException
	{
	
	
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
   
	
	
	if(fileName.length() > 3 ) {
	category.setImage(fileName);
     
	
    Category savedCategory = categoryService.updateCategory(category);

    String uploadDir = "./category-photos/" + savedCategory.getId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
    else
    {
 	   //category.setImage("noimg.png");
 	   Category savedCategory = categoryService.updateCategory(category);
    }
    
    return "redirect:/category/list";
   
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String deletecategory(@PathVariable int id, Model m)
	
	{
		categoryService.deleteCategory(id);
		return "redirect:/category/list";
	}
	
}
